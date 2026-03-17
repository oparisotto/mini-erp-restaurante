package com.restaurante.api.repository

import com.restaurante.api.model.TaxaEntrega
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface TaxaEntregaRepository : JpaRepository<TaxaEntrega, UUID> {
    fun findByBairro(bairro: String): TaxaEntrega?
}