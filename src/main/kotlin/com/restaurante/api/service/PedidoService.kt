package com.restaurante.api.service

import com.restaurante.api.model.Pedido
import com.restaurante.api.repository.PedidoRepository
import org.springframework.stereotype.Service

@Service
class pedidoService(
    private val pedidoRepository: PedidoRepository
) {
    fun listar(): List<Pedido>{
        return pedidoRepository.findAll()
    }

    fun salvar(pedido: Pedido): Pedido{
        return pedidoRepository.save(pedido)
    }
}