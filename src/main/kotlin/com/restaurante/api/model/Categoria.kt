package com.restaurante.api.model

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "categorias")
data class Categoria(
    @Id
    val id: UUID = UUID.randomUUID(),

    @Column(name = "nome", nullable = false)
    var nome: String = "",

    @Column(name = "ativo", nullable = false)
    var ativo: Boolean = true,
)