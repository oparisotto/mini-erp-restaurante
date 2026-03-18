package com.restaurante.api.controller

import com.restaurante.api.model.Categoria
import com.restaurante.api.model.Produto
import com.restaurante.api.repository.CategoriaRepository
import com.restaurante.api.service.CategoriaService
import com.restaurante.api.service.UserService
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/categorias")
class CategoriaController(
    private val categoriaService: CategoriaService,
) {
    @GetMapping
    fun listar(): List<Categoria> {
        return categoriaService.listar()
    }

    @PostMapping
    fun create(@RequestBody categoria: Categoria): Categoria{
        return categoriaService.salvar(categoria)
    }

    @PutMapping("/{id}")
    fun atualizar(
        @PathVariable id: UUID,
        @RequestBody categoria: Categoria
    ): Categoria {

        return categoriaService.atualizar(id, categoria)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID) {
        categoriaService.deletar(id)
    }
}