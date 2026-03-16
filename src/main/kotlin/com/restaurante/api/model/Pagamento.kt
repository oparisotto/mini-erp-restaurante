package com.restaurante.api.model

import java.util.*
import java.math.BigDecimal
import java.time.LocalDateTime
import jakarta.persistence.*

@Entity
@Table(name = "pagamentos")
data class Pagamento(

    @Id
    var id: UUID = UUID.randomUUID(),

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    val pedido: Pedido? = null,

    @Column(name = "metodo", nullable = false)
    val metodo: String = "",

    @Column(name = "status", nullable = false)
    val status: String = "",

    @Column(name = "valor", nullable = false)
    val valor: BigDecimal = BigDecimal.ZERO,

    @Column(name = "referencia_gateway", nullable = false)
    val referenciaGateway: String? = null,

    @Column(name = "criado_em", nullable = false)
    val criadoEm: LocalDateTime = LocalDateTime.now()
)