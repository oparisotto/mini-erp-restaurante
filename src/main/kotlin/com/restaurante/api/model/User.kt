package com.restaurante.api.model

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "users")
data class User(

    @Id
    val id: UUID = UUID.randomUUID(),

    @Column(name = "nome", nullable = false)
    var nome: String = "",

    @Column(unique = true, nullable = false)
    var email: String = "",

    @Column(name = "telefone", nullable = false)
    var telefone: String = "",

    @Column(name = "senha_hash", nullable = false)
    var senhaHash: String = "",

    @Column(name = "ativo", nullable = false)
    var ativo: Boolean = true,

    @Column(name = "criado_em")
    var criadoEm: LocalDateTime = LocalDateTime.now()
)