package com.restaurante.api.controller

import com.restaurante.api.dto.user.UserRequestDTO
import com.restaurante.api.dto.user.UserResponseDTO
import com.restaurante.api.model.User
import com.restaurante.api.service.UserService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/users")
class UsersController(

    private val userService: UserService
) {

    @GetMapping
    fun listar(): List<UserResponseDTO> {
        return userService.listar()
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: UUID): UserResponseDTO {
        return userService.buscarPorId(id)
    }

    @PostMapping
    fun criar(@RequestBody dto: UserRequestDTO): UserResponseDTO {
        return userService.salvar(dto)
    }

    @PutMapping("/{id}")
    fun atualizar(
        @PathVariable id: UUID,
        @RequestBody dto: UserRequestDTO
    ): UserResponseDTO {
        return userService.atualizar(id, dto)
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: UUID){
        userService.deletar(id)
    }
}