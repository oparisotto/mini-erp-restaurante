package com.restaurante.api.service

import com.restaurante.api.dto.auth.LoginRequestDTO
import com.restaurante.api.dto.auth.LoginResponseDTO
import com.restaurante.api.repository.UserRepository
import com.restaurante.api.security.JwtService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.slf4j.LoggerFactory

@Service
class AuthService (
    private val userRepository: UserRepository,
    private val jwtService: JwtService
){

    private val logger = LoggerFactory.getLogger(AuthService::class.java)

    private val encoder = BCryptPasswordEncoder()

    fun login(dto: LoginRequestDTO): LoginResponseDTO {

        logger.info("Tentativa de login para: {}", dto.email)

        val user = userRepository.findAll()
            .find { it.email == dto.email }
            ?: run {
                logger.warn("Login falhou - email não encontrado: {}", dto.email)
                throw RuntimeException("Email ou senha inválidos")
            }

        if (!encoder.matches(dto.senha, user.senhaHash)){
            logger.warn("Login falhou - senha incorreta para: {}", dto.email)
            throw RuntimeException("Email ou senha inválidos")
        }

        val token = jwtService.gerarToken(user.email, user.role.name)

        logger.info("Login realizado com sucesso para: {} | role: {}", user.email, user.role)

        return LoginResponseDTO(token)
    }
}