package io.github.com.Rubens_Pereira_GTI.despensa.dto;

import java.math.BigDecimal;

public record EstoqueResponseDTO(
        Long id,
        ProdutoResumoDTO nomeProduto,
        BigDecimal quantidade,
        BigDecimal qtdReservada,
        String localizacao
) {
    
}
