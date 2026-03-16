package com.restaurante.api.service

import com.restaurante.api.repository.EnderecoRepository
import com.restaurante.api.model.Endereco
import org.springframework.stereotype.Service

@Service
class EnderecoService(
    private val enderecoRepository: EnderecoRepository
){

    fun listar(): List<Endereco>{
        return enderecoRepository.findAll()
    }

    fun salvar(endereco: Endereco): Endereco{
        return enderecoRepository.save(endereco)
    }
}