package com.restaurante.api.dto.dashboard

import java.math.BigDecimal

data class DashBoardRespondeDTO(
    val totalPedidos: Long,
    val faturamentoTotal: BigDecimal,
    val pedidosPorStatus: Map<String, Long>,
    val pedidosHoje: Long,
    val faturamentoHoje: BigDecimal
)
