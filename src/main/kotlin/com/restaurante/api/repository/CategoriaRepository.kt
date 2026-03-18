package com.restaurante.api.repository

import com.restaurante.api.model.Categoria
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface CategoriaRepository : JpaRepository<Categoria, UUID> {
    fun findByAtivoTrue(): List<Categoria>
}