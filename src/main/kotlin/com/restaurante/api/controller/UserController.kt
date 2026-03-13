package com.restaurante.api.controller

import com.restaurante.api.model.User
import com.restaurante.api.repository.UserRepository
import com.restaurante.api.service.UserService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/users")
class UsersController(
    private val repository: UserRepository,
    private val userService: UserService
) {

    @GetMapping
    fun listar(): List<User> {
        return userService.listar()
    }

    @PostMapping
    fun criar(@RequestBody user: User): User {
        return userService.salvar(user)
    }

    @PutMapping("/{id}")
    fun atualizar(
        @PathVariable id: UUID,
        @RequestBody user: User
    ): User {
        return userService.atualizar(id, user)
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: UUID){
        userService.deletar(id)
    }
}