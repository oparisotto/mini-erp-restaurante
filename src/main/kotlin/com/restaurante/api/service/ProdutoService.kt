package com.restaurante.api.service

import com.restaurante.api.repository.ProdutoRepository
import com.restaurante.api.model.Produto
import org.springframework.stereotype.Service
import java.util.UUID

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

    fun atualizar(id: UUID, produto: Produto): Produto{
        val existente = produtoRepository.findById(id)
            .orElseThrow { RuntimeException("Produto não encontrado") }

        val atualizado = existente.copy(
            nome = produto.nome,
            descricao = produto.descricao,
            preco = produto.preco,
            ativo = produto.ativo,
            categoria = produto.categoria,
        )

        return produtoRepository.save(atualizado)
    }

    fun deletar(id: UUID){
        val produto = produtoRepository.findById(id)
            .orElseThrow { RuntimeException("Produto não encontrado") }

        produto.ativo = false

        produtoRepository.save(produto)
    }

}