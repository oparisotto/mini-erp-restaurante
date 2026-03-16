package com.restaurante.api.service

import com.restaurante.api.repository.CupomRepository
import com.restaurante.api.model.Cupom
import org.springframework.stereotype.Service

@Service
class CupomService(
    private val cupomRepository: CupomRepository
){
    fun listar(): List<Cupom>{
        return cupomRepository.findAll()
    }

    fun salvar(cupom: Cupom): Cupom{
        return cupomRepository.save(cupom)
    }

    fun buscarPorCodigo(codigo: String): Cupom?{
        return cupomRepository.findByCodigo(codigo)
    }
}