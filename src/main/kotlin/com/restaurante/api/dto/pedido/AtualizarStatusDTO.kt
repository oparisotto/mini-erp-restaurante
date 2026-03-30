package com.restaurante.api.dto.pedido

import jakarta.validation.constraints.NotBlank

data class AtualizarStatusDTO(

    @field:NotBlank(message = "Status é obrigatório")
    val status: String,
)