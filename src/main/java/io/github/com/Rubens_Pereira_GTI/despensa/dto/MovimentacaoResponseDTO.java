package io.github.com.Rubens_Pereira_GTI.despensa.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.github.com.Rubens_Pereira_GTI.despensa.entity.TipoMovimentacao;

public record MovimentacaoResponseDTO(
    Long id,
    ProdutoResumoDTO produtoResponse,
    TipoMovimentacao tipoMovimentacao,
    BigDecimal qtd,
    BigDecimal qtdAnterior,
    BigDecimal qtdNova,
    String motivo,
    LocalDateTime dataMovimentacao

) {
    
}
