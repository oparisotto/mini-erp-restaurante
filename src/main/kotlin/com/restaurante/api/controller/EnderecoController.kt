package com.restaurante.api.controller

import com.restaurante.api.model.Endereco
import com.restaurante.api.repository.EnderecoRepository
import com.restaurante.api.service.EnderecoService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/enderecos")
class EnderecoController(
    private val enderecoService: EnderecoService
){

    @GetMapping
    fun listar(): List<Endereco> {
        return enderecoService.listar()
    }

    @PostMapping
    fun criar(@RequestBody endereco: Endereco): Endereco{
        return enderecoService.salvar(endereco)
    }

}