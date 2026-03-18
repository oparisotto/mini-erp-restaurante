package com.restaurante.api.service

import com.restaurante.api.repository.CupomRepository
import com.restaurante.api.model.Cupom
import org.springframework.stereotype.Service
import java.util.UUID

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

    fun atualizar(id: UUID, cupom: Cupom): Cupom {
        val existente = cupomRepository.findById(id)
            .orElseThrow { RuntimeException("Cupom não encontrado") }

        val atualizado = existente.copy(
            codigo = cupom.codigo,
            descricao = cupom.descricao,
            tipo = cupom.tipo,
            valor = cupom.valor,
            dataInicio = cupom.dataInicio,
            dataFim = cupom.dataFim,
            ativo = cupom.ativo,
        )

        return cupomRepository.save(atualizado)
    }

    fun deletar(id: UUID){
        val cupom = cupomRepository.findById(id)
            .orElseThrow { RuntimeException("Cupom não encontrado") }

        cupom.ativo = false

        cupomRepository.save(cupom)
    }
}