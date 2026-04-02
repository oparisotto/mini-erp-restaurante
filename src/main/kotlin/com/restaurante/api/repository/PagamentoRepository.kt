package com.restaurante.api.repository

import com.restaurante.api.model.Pagamento
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface PagamentoRepository : JpaRepository<Pagamento, UUID> {
    fun findByReferenciaGateway(referenciaGateway: String): Pagamento?
}
