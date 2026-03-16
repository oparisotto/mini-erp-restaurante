package com.restaurante.api.service

import com.restaurante.api.model.PedidoItem
import com.restaurante.api.repository.PedidoItemRepository
import org.springframework.stereotype.Service

@Service
class PedidoItemService(
    private val pedidoItemRepository: PedidoItemRepository
){
    fun listar(): List<PedidoItem>{
        return pedidoItemRepository.findAll()
    }

    fun salvar(pedidoItem: PedidoItem): PedidoItem{
        return pedidoItemRepository.save(pedidoItem)
    }
}