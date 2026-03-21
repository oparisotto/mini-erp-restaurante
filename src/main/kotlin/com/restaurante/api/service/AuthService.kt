package com.restaurante.api.service

import com.restaurante.api.dto.auth.LoginRequestDTO
import com.restaurante.api.dto.auth.LoginResponseDTO
import com.restaurante.api.repository.UserRepository
import com.restaurante.api.security.JwtService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService (
    private val userRepository: UserRepository,
    private val jwtService: JwtService
){

    private val encoder = BCryptPasswordEncoder()

    fun login(dto: LoginRequestDTO): LoginResponseDTO {

        val user = userRepository.findAll()
            .find { it.email == dto.email }
            ?: throw RuntimeException("Email ou senha inválidos")

        if (!encoder.matches(dto.senha, user.senhaHash)){
            throw RuntimeException("Email ou senha inválidos")
        }

        val token = jwtService.gerarToken(user.email)

        return LoginResponseDTO(token)
    }
}