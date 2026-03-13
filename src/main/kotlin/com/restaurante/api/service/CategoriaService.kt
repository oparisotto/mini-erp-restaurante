package com.restaurante.api.service

import com.restaurante.api.repository.CategoriaRepository
import com.restaurante.api.model.Categoria
import org.springframework.stereotype.Service

@Service
class CategoriaService(
    val categoriaRepository: CategoriaRepository
) {
    fun listar(): List<Categoria>{
        return categoriaRepository.findAll()
    }

    fun salvar(categoria: Categoria): Categoria {
        return categoriaRepository.save(categoria)
    }
}