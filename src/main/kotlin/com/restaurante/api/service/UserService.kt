package com.restaurante.api.service

import com.restaurante.api.dto.user.UserRequestDTO
import com.restaurante.api.dto.user.UserResponseDTO
import com.restaurante.api.model.User
import com.restaurante.api.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun listar(): List<UserResponseDTO>{
        return userRepository.findAll().map {
            UserResponseDTO(
                id = it.id,
                nome = it.nome,
                email = it.email,
                telefone = it.telefone,
                ativo = it.ativo,
            )
        }
    }

    fun buscarPorId(id: UUID): UserResponseDTO {
        val user = userRepository.findById(id)
            .orElseThrow { RuntimeException("Usuario não encontrado") }

        return UserResponseDTO(
            id = user.id,
            nome = user.nome,
            email = user.email,
            telefone = user.telefone,
            ativo = user.ativo
        )
    }

    fun salvar(dto: UserRequestDTO): UserResponseDTO{

        val user = User(
            nome = dto.nome,
            email = dto.email,
            telefone = dto.telefone,
            senhaHash = dto.senha,
            ativo = true
        )

        val salvo = userRepository.save(user)

        return UserResponseDTO(
            id = salvo.id,
            nome = salvo.nome,
            email = salvo.email,
            telefone = salvo.telefone,
            ativo = salvo.ativo
        )
    }

    fun atualizar(id: UUID, user: User): User {
        val existente = userRepository.findById(id)
            .orElseThrow { RuntimeException("Usuario não encontrado") }

        val atualizado = existente.copy(
            nome = user.nome,
            email = user.email,
            telefone = user.telefone
        )
        return userRepository.save(atualizado)
    }

    fun deletar(id: UUID) {
       val user = userRepository.findById(id)
           .orElseThrow { RuntimeException("Usuario não encontrado") }

        userRepository.delete(user)
    }
}