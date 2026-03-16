package com.restaurante.api.controller

import com.restaurante.api.model.PedidoItem
import com.restaurante.api.service.PedidoItemService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pedido_itens")
class PedidoItem(
    private val pedidoItemService: PedidoItemService
){
    @GetMapping
    fun listar(): List<PedidoItem>{
        return pedidoItemService.listar()
    }

    @PostMapping
    fun criar(@RequestBody pedidoItem: PedidoItem): PedidoItem{
        return pedidoItemService.salvar(pedidoItem)
    }
}