package com.restaurante.api.service

import com.restaurante.api.model.User
import com.restaurante.api.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun listar(): List<User>{
        return userRepository.findAll()
    }

    fun salvar(user: User): User {
        return userRepository.save(user)
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