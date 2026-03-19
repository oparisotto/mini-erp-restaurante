package com.restaurante.api.dto.user

data class UserRequestDTO(
    val nome: String,
    val email: String,
    val telefone: String,
    val senha: String
)