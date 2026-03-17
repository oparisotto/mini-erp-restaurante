package com.restaurante.api.model

import java.util.*
import java.math.BigDecimal
import jakarta.persistence.*

@Entity
@Table(name = "taxas_entrega")
data class TaxaEntrega(

    @Id
    val id: UUID = UUID.randomUUID(),

    @Column(name = "bairro", nullable = false)
    val bairro: String = "",

    @Column(name = "valor", nullable = false)
    val valor: BigDecimal = BigDecimal.ZERO,

    @Column(name = "ativo", nullable = false)
    val ativo: Boolean = true
)