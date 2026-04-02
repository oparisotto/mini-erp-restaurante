package com.restaurante.api.service

import com.mercadopago.MercadoPagoConfig
import com.mercadopago.client.payment.PaymentClient
import com.mercadopago.client.payment.PaymentCreateRequest
import com.mercadopago.client.payment.PaymentPayerRequest
import com.mercadopago.resources.payment.Payment
import com.restaurante.api.dto.pagamento.PagamentoRequestDTO
import com.restaurante.api.dto.pagamento.PagamentoResponseDTO
import com.restaurante.api.model.Pagamento
import com.restaurante.api.repository.PagamentoRepository
import com.restaurante.api.repository.PedidoRepository
import jakarta.annotation.PostConstruct
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class PagamentoService(
    private val pagamentoRepository: PagamentoRepository,
    private val pedidoRepository: PedidoRepository,
) {
   val logger = LoggerFactory.getLogger(PagamentoService::class.java)

    @Value("\${mercadopago.access-token}")
    private lateinit var accessToken: String

    @PostConstruct
    fun init() {
        MercadoPagoConfig.setAccessToken(accessToken)
        logger.info("MercadoPago configurado com sucesso!")
    }

    fun listar(): List<Pagamento> {
        return pagamentoRepository.findAll()
    }

    fun criar(dto: PagamentoRequestDTO): PagamentoResponseDTO {

        logger.info("Iniciado pagamento para pedido: {} via {}", dto.pedidoId, dto.metodo)

        val pedido = pedidoRepository.findById(dto.pedidoId).orElseThrow {
            RuntimeException("Pedido não encontrado")
        }

        if (pedido.status != "aguardando_pagamento") {
            throw RuntimeException("Pedido não esta aguardando pagamento. Status atual: ${pedido.status}")
        }

        val metodoPagamento = when (dto.metodo.lowercase()) {
            "pix"       -> "pix"
            "cartao"    -> "credit_card"
            "dinheiro"  -> "cash"
            else -> throw RuntimeException("Método de pagamento invalido: ${dto.metodo}")
        }

        val request = PaymentCreateRequest.builder()
            .transactionAmount(pedido.total)
            .description("Pedido #${pedido.id}")
            .paymentMethodId(metodoPagamento)
            .payer(
                PaymentPayerRequest.builder()
                    .email(pedido.user?.email)
                    .build()
            )
            .build()

        val client = PaymentClient()
        val payment: Payment = client.create(request)

        logger.info("Pagamento criado no MercadoPago. ID: {} Status: {}", payment.id, payment.status)

        val statusMapeado = when (payment.status) {
            "approved"  -> "confirmado"
            "pending"   -> "pendente"
            "cancelled" -> "cancelado"
            "rejected"  -> "cancelado"
            else        -> "pendente"
        }

        val pagamento = pagamentoRepository.save(
            Pagamento(
                pedido = pedido,
                metodo = dto.metodo,
                status = statusMapeado,
                valor = pedido.total,
                referenciaGateway = payment.id.toString()
            )
        )

        return PagamentoResponseDTO(
            id = pagamento.id,
            status = statusMapeado,
            metodo = dto.metodo,
            valor = pedido.total,
            linkPagamento = payment.pointOfInteraction?.transactionData?.ticketUrl,
            qrCodePix = payment.pointOfInteraction?.transactionData?.qrCode
        )
    }

}