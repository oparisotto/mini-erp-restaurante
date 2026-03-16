package com.restaurante.api.controller

import com.restaurante.api.service.CupomService
import com.restaurante.api.model.Cupom
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cupons")
class CupomController(
    val cupomService: CupomService
){
    @GetMapping
    fun listar(): List<Cupom>{
        return cupomService.listar()
    }

    @PostMapping
    fun criar(@RequestBody cupom: Cupom): Cupom{
        return cupomService.salvar(cupom)
    }

    @GetMapping("/{codigo}")
    fun buscar(@PathVariable codigo: String): Cupom?{
        return cupomService.buscarPorCodigo(codigo)
    }

}