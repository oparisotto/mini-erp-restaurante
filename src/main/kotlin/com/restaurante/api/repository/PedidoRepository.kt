package com.restaurante.api.repository

import com.restaurante.api.model.Pedido
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.math.BigDecimal
import java.util.*
import java.time.LocalDateTime

interface PedidoRepository : JpaRepository<Pedido, UUID> {

    @Query("SELECT COUNT(p) FROM Pedido p WHERE p.criadoEm >= :inicio AND p.criadoEm <= :fim")
    fun contarPedidosPorPeriodo(
        @Param("inicio") inicio: LocalDateTime,
        @Param("fim") fim: LocalDateTime
    ): Long

    @Query("SELECT COALESCE(SUM(p.total), 0) FROM Pedido p WHERE p.status != 'cancelado'")
    fun faturamentoTotal(): BigDecimal

    @Query("SELECT COALESCE(SUM(p.total), 0) FROM Pedido p WHERE p.criadoEm >= :inicio AND p.criadoEm <= :fim AND p.status != 'cancelado'")
    fun faturamentoPorPeriodo(
        @Param("inicio") inicio: LocalDateTime,
        @Param("fim") fim: LocalDateTime
    ): BigDecimal

    @Query("SELECT p.status, COUNT(p) FROM Pedido p GROUP BY p.status")
    fun contarPorStatus(): List<Array<Any>>

    @Query("SELECT p FROM Pedido p WHERE (:status IS NULL OR p.status = :status) AND (:email IS NULL OR p.user.email = :email)")
    fun buscarComFiltros(
        @Param("status") status: String?,
        @Param("email") email: String?,
        pageable: Pageable
    ) : Page<Pedido>
}