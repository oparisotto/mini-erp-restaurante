package com.restaurante.api.controller

import com.restaurante.api.dto.pedido.AtualizarStatusDTO
import com.restaurante.api.dto.pedido.PedidoRequestDTO
import com.restaurante.api.model.Pedido
import com.restaurante.api.service.PedidoService

import jakarta.validation.Valid

import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*

import java.util.*

@RestController
@RequestMapping("/pedidos")
class PedidoController(
    private val PedidoService: PedidoService
){

    @GetMapping
    fun listar(
        @RequestParam(required = false) status: String?,
        @RequestParam(required = false) email: String?,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): Page<Pedido>{
        return PedidoService.listar(status, email, page, size)
    }

    @PostMapping
    fun criar(@Valid @RequestBody dto: PedidoRequestDTO): Pedido{
        return PedidoService.criar(dto)
    }

    @PutMapping("/{id}/status")
    fun atualizarSatus(
        @PathVariable id: UUID,
        @Valid @RequestBody dto: AtualizarStatusDTO
    ): Pedido{
        return PedidoService.atualizarStatus(id, dto.status)
    }
}