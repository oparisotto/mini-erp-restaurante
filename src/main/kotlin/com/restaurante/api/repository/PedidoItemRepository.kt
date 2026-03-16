package com.restaurante.api.repository

import com.restaurante.api.model.PedidoItem
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PedidoItemRepository : JpaRepository<PedidoItem, UUID>{}