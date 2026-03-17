package com.restaurante.api.controller

import com.restaurante.api.model.TaxaEntrega
import com.restaurante.api.service.TaxaEntregaService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/taxas_entrega")
class TaxaEntregaController(
    private val taxaEntregaService: TaxaEntregaService
){
    @GetMapping
    fun listar(): List<TaxaEntrega>{
        return taxaEntregaService.listar()
    }

    @PostMapping
    fun criar (@RequestBody taxaEntrega: TaxaEntrega): TaxaEntrega{
        return taxaEntregaService.salvar(taxaEntrega)
    }

    @GetMapping("/{bairro}")
    fun buscar(@PathVariable bairro: String): TaxaEntrega?{
        return taxaEntregaService.buscarPorBairro(bairro)
    }
}