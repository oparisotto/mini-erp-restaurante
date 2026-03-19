package com.restaurante.api.dto.user

import java.util.UUID

data class UserResponseDTO(
    val id: UUID,
    val nome: String,
    val email: String,
    val telefone: String,
    val ativo: Boolean
)