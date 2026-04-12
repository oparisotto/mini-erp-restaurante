package com.restaurante.api.service

import com.restaurante.api.dto.dashboard.DashBoardRespondeDTO
import com.restaurante.api.repository.PedidoRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalTime

@Service
class DashboardService (
    private val pedidoRepository: PedidoRepository
){
    private val logger = LoggerFactory.getLogger(DashboardService::class.java)

    fun obter(): DashBoardRespondeDTO {
        logger.info("Gerando dashboard")

        val inicioDia = LocalDate.now().atStartOfDay()
        val fimDia = LocalDate.now().atTime(LocalTime.MAX)

        val totalPedidos = pedidoRepository.count()
        val faturamentoTotal = pedidoRepository.faturamentoTotal()
        val pedidosHoje = pedidoRepository.contarPedidosPorPeriodo(inicioDia, fimDia)
        val faturamentoHoje = pedidoRepository.faturamentoPorPeriodo(inicioDia, fimDia)

        val pedidosPorStatus = pedidoRepository.contarPorStatus()
            .associate { it[0].toString() to it[1] as Long }

        logger.info("Dashboard gerado - total pedidos: {}, faturamento: {}", totalPedidos, faturamentoTotal)

        return DashBoardRespondeDTO(
            totalPedidos = totalPedidos,
            faturamentoTotal = faturamentoTotal,
            pedidosPorStatus = pedidosPorStatus,
            pedidosHoje = pedidosHoje,
            faturamentoHoje = faturamentoHoje
        )
    }
}