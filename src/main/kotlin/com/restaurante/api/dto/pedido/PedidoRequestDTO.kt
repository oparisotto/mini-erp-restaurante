package com.restaurante.api.dto.pedido

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.util.UUID

data class PedidoRequestDTO(

    @field:NotNull(message = "Endereço é obrigatório")
    val enderecoId: UUID,

    @field:NotEmpty(message = "Pedido não pode ser vazio")
    val itens: List<ItemRequestDTO>,

    val codigoCupom: String? = null
)

data class ItemRequestDTO(

    @field:NotNull(message = "Produto é obrigatório")
    val produtoId: UUID,

    @field:NotEmpty(message = "Quantidade deve ser no mínimo 1")
    val quantidade: Int
)