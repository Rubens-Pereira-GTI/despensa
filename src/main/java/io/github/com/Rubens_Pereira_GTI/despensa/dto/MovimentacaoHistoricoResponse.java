package io.github.com.Rubens_Pereira_GTI.despensa.dto;

import java.math.BigDecimal;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.TipoMovimentacao;

public record MovimentacaoHistoricoResponse(
    Long id,
    String nomeProduto,
    String siglaUnidadeMedida,
    BigDecimal qtd,
    TipoMovimentacao tipoMovimentacao,
    String motivo
    
) {
    
}
