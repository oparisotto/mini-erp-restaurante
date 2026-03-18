package com.restaurante.api.service

import com.restaurante.api.model.TaxaEntrega
import com.restaurante.api.repository.TaxaEntregaRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class TaxaEntregaService(
    private val taxaEntregaRepository: TaxaEntregaRepository
){
    fun listar(): List<TaxaEntrega>{
        return taxaEntregaRepository.findAll()
    }

    fun salvar(taxaEntrega: TaxaEntrega): TaxaEntrega{
        return taxaEntregaRepository.save(taxaEntrega)
    }

    fun buscarPorBairro(bairro: String): TaxaEntrega?{
        return taxaEntregaRepository.findByBairro(bairro)
    }

    fun atualizar(id: UUID, taxaEntrega: TaxaEntrega): TaxaEntrega{
        val existente = taxaEntregaRepository.findById(id)
            .orElseThrow { RuntimeException("Taxa de entrega não encontrada") }

        val atualizado = existente.copy(
            bairro = taxaEntrega.bairro,
            valor = taxaEntrega.valor,
            ativo = taxaEntrega.ativo,
        )

        return taxaEntregaRepository.save(atualizado)
    }

    fun deletar(id: UUID){
        val taxaEntrega = taxaEntregaRepository.findById(id)
            .orElseThrow { RuntimeException("Taxa de entrega não encontrada") }

        taxaEntrega.ativo  = false

        taxaEntregaRepository.save(taxaEntrega)
    }
}