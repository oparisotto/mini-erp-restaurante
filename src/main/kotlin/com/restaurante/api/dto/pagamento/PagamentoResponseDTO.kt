package com.restaurante.api.dto.pagamento

import java.math.BigDecimal
import java.util.UUID

data class PagamentoResponseDTO(
    val id: UUID,
    val status: String,
    val metodo: String,
    val valor: BigDecimal,
    val linkPagamento: String? = null,
    val qrCodePix: String? = null
)
