package com.restaurante.api.service

import com.restaurante.api.model.User
import com.restaurante.api.repository.UserRepository
import org.springframework.stereotype.Service

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
}