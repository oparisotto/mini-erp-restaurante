package com.restaurante.api.service

import com.restaurante.api.dto.pedido.PedidoRequestDTO
import com.restaurante.api.model.Pedido
import com.restaurante.api.model.PedidoItem
import com.restaurante.api.model.Produto
import com.restaurante.api.repository.*

import jakarta.validation.constraints.Email

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.slf4j.LoggerFactory

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@Service
class PedidoService (
    private val PedidoRepository: PedidoRepository,
    private val PedidoItemRepository: PedidoItemRepository,
    private val ProdutoRepository: ProdutoRepository,
    private val EnderecoRepository: EnderecoRepository,
    private val UserRepository: UserRepository,
    private val CupomRepository: CupomRepository,
    private val TaxaEntregaRepository: TaxaEntregaRepository,
) {

    private val logger = LoggerFactory.getLogger(PedidoService::class.java)

    fun criar(dto: PedidoRequestDTO): Pedido {

        val email = SecurityContextHolder.getContext().authentication.name
        logger.info("Criado pedido para usuário: {}", email)

        val user = UserRepository.findByEmail(email)
            ?: throw RuntimeException("Usuário não encontrado")

        if (dto.itens.isEmpty()) throw RuntimeException("Pedido não pode ser vazio")

        val endereco = EnderecoRepository.findById(dto.enderecoId).orElseThrow{
            RuntimeException("Endereço não encontrado")
        }

        val itensPedido = dto.itens.map { itemDTO ->
            val produto = ProdutoRepository.findById(itemDTO.produtoId).orElseThrow {
                RuntimeException("Produto ${itemDTO.produtoId} não encontrado")
            }

            if (!produto.ativo) throw RuntimeException("Produto ${produto.nome} não está disponivel")
            if (itemDTO.quantidade <= 0) throw RuntimeException("Quantidade invalida para ${produto.nome}")

            logger.info("Item adicionado: {} x{}", produto.nome, itemDTO.quantidade)

            PedidoItem(
                produto = produto,
                quantidade = itemDTO.quantidade,
                precoUnitario = produto.preco,
                subtotal = produto.preco.multiply(BigDecimal(itemDTO.quantidade))
            )
        }

        val subtotal = itensPedido.fold(BigDecimal.ZERO) { acc, item -> acc + item.subtotal }

        val taxaEntrega = TaxaEntregaRepository.findByBairro(endereco.bairro ?: "")
            ?: throw RuntimeException("Taxa de entrega não encontrada para o bairro: ${endereco.bairro}")

        if (!taxaEntrega.ativo) throw RuntimeException("Entrega não disponível para o bairro: ${endereco.bairro}")

        val valorTaxa = taxaEntrega.valor

        var desconto = BigDecimal.ZERO
        if (dto.codigoCupom != null) {
            val cupom = CupomRepository.findByCodigo(dto.codigoCupom)
                ?: throw RuntimeException("Cupom não encontrado")

            val agora = LocalDateTime.now()

            if (!cupom.ativo) throw RuntimeException("Cupon inativo")
            if (cupom.dataFim != null && agora.isAfter(cupom.dataFim)) throw RuntimeException("Cupon expirado")
            if (cupom.dataInicio != null && agora.isBefore(cupom.dataInicio)) throw RuntimeException("Cupom ainda não valido")
            if (cupom.usado >= cupom.limiteUso) throw RuntimeException("Cupom esgotado")

            desconto = when (cupom.tipo) {
                "PERCENTUAL" -> subtotal.multiply(cupom.valor).divide(BigDecimal(100))
                "FIXO" -> cupom.valor
                else -> BigDecimal.ZERO
            }

            logger.info("Cupom {} aplicado, desconto: {}", cupom.codigo, desconto)

            cupom.usado += 1
            CupomRepository.save(cupom)
        }

        val total = subtotal + valorTaxa - desconto

        val pedido = PedidoRepository.save(
            Pedido(
                user = user,
                endereco = endereco,
                status = "criado",
                subtotal = subtotal,
                taxaEntrega = valorTaxa,
                desconto = desconto,
                total = total,
            )
        )

        itensPedido.forEach { item ->
            item.pedido = pedido
            PedidoItemRepository.save(item)
        }

        logger.info("Pedido {} criado com sucesso. Total: {}", pedido.id, total)

        return pedido
    }

    fun listar(status: String?, email: String?, page: Int, size: Int): Page<Pedido> {
        val pageable = PageRequest.of(page, size, Sort.by("criadoEm").descending())
        logger.info("Listando pedidos - status: {}, email: {}, page: {}, size: {}", status, email, page, size)
        return PedidoRepository.buscarComFiltros(status, email, pageable)
    }

    fun atualizarStatus(id: UUID, novoStatus: String): Pedido {

        logger.info("Atualizando status do pedido {} para {}", id, novoStatus)

        val pedido = PedidoRepository.findById(id).orElseThrow {
            RuntimeException("Pedido não encontrado")
        }

        val transicoesValidas = mapOf(
            "criado"                    to listOf("aguardando_pagamento", "cancelado"),
            "aguardando_pagamento"      to listOf("pago", "cancelado"),
            "pago"                      to listOf("em_preparo", "cancelado"),
            "em_ativo"                  to listOf("pronto", "cancelado"),
            "pronto"                    to listOf("entregue"),
            "entregue"                  to emptyList(),
            "cancelado"                 to emptyList()
        )

        val permitidos = transicoesValidas[pedido.status]
            ?: throw RuntimeException("Status atual inválido: ${pedido.status}")


        if (novoStatus !in permitidos) {
            logger.warn("Transição inválida tentada: {} -> {}", pedido.status, novoStatus)
            throw RuntimeException(
                "Transição inválida: ${pedido.status} -> $novoStatus. Permitidos: $permitidos"
            )
        }

        pedido.status = novoStatus
        logger.info("Status do pedido {} atualizado para {}", id, novoStatus)

        return PedidoRepository.save(pedido)
    }
}