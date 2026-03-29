package com.restaurante.api.dto.pedido

import java.util.UUID

data class PedidoRequestDTO(
    val enderecoId: UUID,
    val itens: List<ItemRequestDTO>,
    val codigoCupom: String? = null
)

data class ItemRequestDTO(
    val produtoId: UUID,
    val quantidade: Int
)