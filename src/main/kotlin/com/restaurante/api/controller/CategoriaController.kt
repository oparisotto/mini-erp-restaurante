package com.restaurante.api.controller

import com.restaurante.api.model.Categoria
import com.restaurante.api.repository.CategoriaRepository
import com.restaurante.api.service.CategoriaService
import com.restaurante.api.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/categorias")
class CategoriaController(
    private val categoriaRepository: CategoriaRepository,
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
}