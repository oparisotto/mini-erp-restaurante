package com.restaurante.api.controller

import com.restaurante.api.model.Produto
import com.restaurante.api.service.ProdutoService
import org.springframework.web.bind.annotation.*
import java.util.*

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

    @PutMapping("/{id}")
    fun atualizar(
        @PathVariable id: UUID,
        @RequestBody produto: Produto
    ): Produto {

        return produtoService.atualizar(id, produto)
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: UUID){
        produtoService.deletar(id)
    }
}