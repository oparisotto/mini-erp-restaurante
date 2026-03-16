package com.restaurante.api.model

import java.util.*
import java.math.BigDecimal
import java.time.LocalDateTime
import jakarta.persistence.*

@Entity
@Table(name = "cupons")
data class Cupom(

    @Id
    var id: UUID = UUID.randomUUID(),

    @Column(name = "codigo", unique = true, nullable = false)
    var codigo: String = "",

    @Column(name = "descricao", nullable = false)
    var descricao: String = "",

    @Column(name = "tipo", nullable = false)
    var tipo: String = "",

    @Column(name = "valor", nullable = false)
    var valor: BigDecimal = BigDecimal.ZERO,

    @Column(name = "data_inicio")
    var dataInicio: LocalDateTime? = null,

    @Column(name = "data_fim")
    var dataFim: LocalDateTime? = null,

    @Column(name = "limite_uso")
    var limiteUso: Int = 0,

    @Column(name = "usado", nullable = false)
    var usado: Int = 0,

    @Column(name = "ativo", nullable = false)
    var ativo: Boolean = true,
)