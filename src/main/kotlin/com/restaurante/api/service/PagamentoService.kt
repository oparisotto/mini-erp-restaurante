package com.restaurante.api.service

import com.restaurante.api.repository.PagamentoRepository
import com.restaurante.api.model.Pagamento
import org.springframework.stereotype.Service

@Service
data class PagamentoService(
    private val pagamentoRepository: PagamentoRepository
){
    fun listar(): List<Pagamento>{
        return pagamentoRepository.findAll()
    }

    fun salvar(pagamento: Pagamento): Pagamento{
        return pagamentoRepository.save(pagamento)
    }
}