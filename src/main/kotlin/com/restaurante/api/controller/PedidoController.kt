package com.restaurante.api.controller

import com.restaurante.api.dto.pedido.AtualizarStatusDTO
import com.restaurante.api.dto.pedido.PedidoRequestDTO
import com.restaurante.api.model.Pedido
import com.restaurante.api.service.PedidoService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/pedidos")
class PedidoController(
    private val PedidoService: PedidoService
){

    @GetMapping
    fun listar(): List<Pedido>{
        return PedidoService.listar()
    }

    @PostMapping
    fun criar(@RequestBody dto: PedidoRequestDTO): Pedido{
        return PedidoService.criar(dto)
    }

    @PutMapping("/{id}/status")
    fun atualizarSatus(
        @PathVariable id: UUID,
        @RequestBody dto: AtualizarStatusDTO
    ): Pedido{
        return PedidoService.atualizarStatus(id, dto.status)
    }
}