package com.restaurante.api.controller

import com.restaurante.api.model.Produto
import com.restaurante.api.service.ProdutoService

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/produtos")
class ProdutoController(
    private val produtoService: ProdutoService
) {
    @GetMapping
    fun listar(): List<Produto>{
        return produtoService.listar()
    }

    @PostMapping
    fun criar(@RequestBody produto: Produto): Produto {
        return produtoService.salvar(produto)
    }
}