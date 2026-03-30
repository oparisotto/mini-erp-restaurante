package com.restaurante.api.controller

import com.restaurante.api.dto.auth.LoginRequestDTO
import com.restaurante.api.dto.auth.LoginResponseDTO
import com.restaurante.api.service.AuthService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController (
    private val authService: AuthService
){
    @PostMapping("/login")
    fun login(@Valid @RequestBody dto: LoginRequestDTO): LoginResponseDTO {
        return authService.login(dto)
    }
}