package com.restaurante.api.model.user

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Enumerated
import jakarta.persistence.EnumType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

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
    var criadoEm: LocalDateTime = LocalDateTime.now(),

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    var role: UserRole = UserRole.CLIENT
)

