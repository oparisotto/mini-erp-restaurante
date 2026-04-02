package com.restaurante.api.dto.pagamento

import java.util.UUID

data class PagamentoRequestDTO(
    val pedidoId: UUID,
    val metodo: String,
)