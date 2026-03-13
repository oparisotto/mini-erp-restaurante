package com.restaurante.api.service

import com.restaurante.api.repository.ProdutoRepository
import com.restaurante.api.model.Produto
import org.springframework.stereotype.Service

@Service
class ProdutoService(
    private val produtoRepository: ProdutoRepository
) {
    fun listar(): List<Produto>{
        return produtoRepository.findAll()
    }

    fun salvar(produto: Produto): Produto{
        return produtoRepository.save(produto)
    }
}