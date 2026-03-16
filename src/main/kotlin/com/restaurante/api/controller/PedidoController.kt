package com.restaurante.api.controller

import com.restaurante.api.model.Pedido
import com.restaurante.api.service.pedidoService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pedidos")
class PedidoController(
    private val pedidoService: pedidoService
){

    @GetMapping
    fun listar(): List<Pedido>{
        return pedidoService.listar()
    }

    @PostMapping
    fun salvar(@RequestBody pedido: Pedido): Pedido{
        return pedidoService.salvar(pedido)
    }
}