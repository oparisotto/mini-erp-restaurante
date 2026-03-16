package com.restaurante.api.repository

import com.restaurante.api.model.Endereco
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface EnderecoRepository : JpaRepository<Endereco, UUID> {}