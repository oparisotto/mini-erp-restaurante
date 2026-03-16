package com.restaurante.api.repository

import com.restaurante.api.model.Cupom
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CupomRepository : JpaRepository<Cupom, UUID>{
    fun findByCodigo(codigo: String): Cupom?
}