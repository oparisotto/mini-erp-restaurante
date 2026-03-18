package com.restaurante.api.model

import java.util.*
import java.math.BigDecimal
import jakarta.persistence.*

@Entity
@Table(name = "taxas_entrega")
data class TaxaEntrega(

    @Id
    var id: UUID = UUID.randomUUID(),

    @Column(name = "bairro", nullable = false)
    var bairro: String = "",

    @Column(name = "valor", nullable = false)
    var valor: BigDecimal = BigDecimal.ZERO,

    @Column(name = "ativo", nullable = false)
    var ativo: Boolean = true,
)