package io.github.com.Rubens_Pereira_GTI.despensa.dto;

import java.math.BigDecimal;

import io.github.com.Rubens_Pereira_GTI.despensa.entity.TipoMovimentacao;

public record MovimentacaoDTO(
    
    Long produtoId,
    Long localId,
    BigDecimal quantidade,
    TipoMovimentacao tipoMovimentacao,
    String motivo

) {
    
}
