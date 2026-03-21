package com.restaurante.api.dto.auth

data class LoginRequestDTO(
    val email: String,
    val senha: String
)