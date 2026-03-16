package com.restaurante.api.controller

import com.restaurante.api.model.Pagamento
import com.restaurante.api.service.PagamentoService
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
    fun criar(@RequestBody pagamento: Pagamento): Pagamento{
        return pagamentoService.salvar(pagamento)
    }
}

