package com.restaurante.api.repository

import com.restaurante.api.model.Pedido
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface PedidoRepository : JpaRepository<Pedido, UUID> {

    @Query("SELECT p FROM Pedido p WHERE (:status IS NULL OR p.status = :status) AND (:email IS NULL OR p.user.email = :email)")
    fun buscarComFiltros(
        @Param("status") status: String?,
        @Param("email") email: String?,
        pageable: Pageable
    ) : Page<Pedido>
}