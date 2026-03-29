package com.restaurante.api.model

import com.restaurante.api.model.user.User
import java.util.*
import java.time.LocalDateTime
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "pedidos")
data class Pedido(

    @Id
    var id: UUID = UUID.randomUUID(),

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    var user: User? = null,

    @ManyToOne
    @JoinColumn(name = "endereco_id", nullable = false)
    var endereco: Endereco? = null,

    @Column(name = "status", nullable = false)
    var status: String = "",

    @Column(name = "subtotal", nullable = false)
    var subtotal: BigDecimal = BigDecimal.ZERO,

    @Column(name = "taxa_entrega", nullable = false)
    var taxaEntrega: BigDecimal = BigDecimal.ZERO,

    @Column(name = "desconto", nullable = false)
    var desconto: BigDecimal = BigDecimal.ZERO,

    @Column(name = "total", nullable = false)
    var total: BigDecimal = BigDecimal.ZERO,

    @Column(name = "criado_em", nullable = false)
    var criadoEm: LocalDateTime = LocalDateTime.now()
)