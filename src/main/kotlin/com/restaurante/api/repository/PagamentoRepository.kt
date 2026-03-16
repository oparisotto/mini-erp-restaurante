package com.restaurante.api.repository

import com.restaurante.api.model.Pagamento
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PagamentoRepository : JpaRepository<Pagamento, UUID> {}
