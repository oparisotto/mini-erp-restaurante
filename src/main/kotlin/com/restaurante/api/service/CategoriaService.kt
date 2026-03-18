package com.restaurante.api.service

import com.restaurante.api.repository.CategoriaRepository
import com.restaurante.api.model.Categoria
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CategoriaService(
    private val categoriaRepository: CategoriaRepository
) {
    fun listar(): List<Categoria>{
        return categoriaRepository.findByAtivoTrue()
    }

    fun salvar(categoria: Categoria): Categoria {
        return categoriaRepository.save(categoria)
    }

    fun atualizar(id: UUID, categoria: Categoria): Categoria{
        val existente = categoriaRepository.findById(id)
            .orElseThrow { RuntimeException("Categoria não encontrada") }

        val atualizado = existente.copy(
            nome = categoria.nome,
        )

        return categoriaRepository.save(atualizado)
    }

    fun deletar(id: UUID){
        val categoria = categoriaRepository.findById(id)
            .orElseThrow { RuntimeException("Categoria não encontrada") }

        categoria.ativo = false

        categoriaRepository.save(categoria)


    }
}