package com.restaurante.api.controller

import com.restaurante.api.dto.pagamento.PagamentoRequestDTO
import com.restaurante.api.dto.pagamento.PagamentoResponseDTO
import com.restaurante.api.model.Pagamento
import com.restaurante.api.service.PagamentoService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pagamentos")
class PagamentoController(
    private val pagamentoService: PagamentoService
){
    @GetMapping
    fun listar():List<Pagamento>{
        return pagamentoService.listar()
    }

    @PostMapping
    fun criar(@Valid @RequestBody dto: PagamentoRequestDTO): PagamentoResponseDTO {
        return pagamentoService.criar(dto)
    }
}

