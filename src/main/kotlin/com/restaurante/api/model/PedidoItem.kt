package com.restaurante.api.model

import java.util.*
import java.math.BigDecimal
import jakarta.persistence.*

@Entity
@Table(name = "pedido_itens")
data class PedidoItem(

    @Id
    var id: UUID = UUID.randomUUID(),

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    var pedido: Pedido? = null,

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    var produto: Produto? = null,

    @Column(name = "quantidade", nullable = false)
    var quantidade: Int = 0,

    @Column(name = "preco_unitario", nullable = false)
    var precoUnitario: BigDecimal = BigDecimal.ZERO,

    @Column(name = "subtotal", nullable = false)
    var subtotal: BigDecimal = BigDecimal.ZERO
)