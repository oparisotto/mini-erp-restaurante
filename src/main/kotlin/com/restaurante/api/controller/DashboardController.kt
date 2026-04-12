package com.restaurante.api.controller

import com.restaurante.api.dto.dashboard.DashBoardRespondeDTO
import com.restaurante.api.service.DashboardService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/dashboard")
class DashboardController (
    private val dashboardService: DashboardService
){

    @GetMapping
    fun obter(): DashBoardRespondeDTO{
        return dashboardService.obter()
    }
}
