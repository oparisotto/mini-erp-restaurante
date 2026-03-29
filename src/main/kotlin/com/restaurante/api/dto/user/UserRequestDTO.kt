package com.restaurante.api.dto.user

import jakarta.validation.constraints.NotBlank

data class UserRequestDTO(

    @field:NotBlank
    val nome: String,

    @field:NotBlank
    val email: String,

    @field:NotBlank
    val telefone: String,

    @field:NotBlank
    val senha: String,

    val role: String? = "CLIENTE"
)