package com.restaurante.api.model

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*


@Entity
@Table(name = "enderecos")
data class Endereco(

    @Id
    var id: UUID = UUID.randomUUID(),

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    var user: User? = null,

    @Column(name = "rua", nullable = false)
    var rua: String? = "",

    @Column(name = "numero", nullable = false)
    var numero: String? = "",

    @Column(name = "complemento", nullable = false)
    var complemento: String? = "",

    @Column(name = "bairro", nullable = false)
    var bairro: String? = "",

    @Column(name = "cidade", nullable = false)
    var cidade: String? = "",

    @Column(name = "estado", nullable = false)
    var estado: String? = "",

    @Column(name = "cep", nullable = false)
    var cep: String? = "",

    @Column(name = "criadoEm", nullable = false)
    var criadoEm: LocalDateTime = LocalDateTime.now()
)