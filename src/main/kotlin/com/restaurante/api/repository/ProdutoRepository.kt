package com.restaurante.api.repository

import com.restaurante.api.model.Produto
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ProdutoRepository : JpaRepository<Produto, UUID>