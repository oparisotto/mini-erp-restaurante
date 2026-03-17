package com.restaurante.api.service

import com.restaurante.api.model.TaxaEntrega
import com.restaurante.api.repository.TaxaEntregaRepository
import org.springframework.stereotype.Service

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
}