package com.restaurante.api.controller

import com.restaurante.api.service.CupomService
import com.restaurante.api.model.Cupom
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/cupons")
class CupomController(
    private val cupomService: CupomService
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

    @PutMapping("/{id}")
    fun atualizar(
        @PathVariable id: UUID,
        @RequestBody cupom: Cupom
    ): Cupom{

        return cupomService.atualizar(id, cupom)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID) {
        cupomService.deletar(id)
    }
}