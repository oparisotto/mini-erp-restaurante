package com.restaurante.api.model

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "produtos")
data class Produto(

    @Id
    var id: UUID = UUID.randomUUID(),

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    val categoria: Categoria? = null,

    @Column(name = "nome", nullable = false)
    var nome: String? = "",

    @Column(name = "descricao", nullable = false)
    var descricao: String? = "",

    @Column(name = "preco", nullable = false)
    var preco: BigDecimal = BigDecimal.ZERO,

    @Column(name = "ativo", nullable = false)
    var ativo: Boolean = true,

    @Column(name = "criado_em")
    var criadoEm: LocalDateTime = LocalDateTime.now()
)