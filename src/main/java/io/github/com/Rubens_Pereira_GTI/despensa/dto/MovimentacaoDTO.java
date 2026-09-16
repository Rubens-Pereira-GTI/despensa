package io.github.com.Rubens_Pereira_GTI.despensa.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import io.github.com.Rubens_Pereira_GTI.despensa.entity.TipoMovimentacao;
import jakarta.validation.constraints.NotNull;

public record MovimentacaoDTO(
    
    Long produtoId,
    Long localId,
    BigDecimal quantidade,
    @NotNull 
    TipoMovimentacao tipoMovimentacao,
    String motivo,

    //Campos do Estoque
    LocalDate dataValidade,
    BigDecimal qtdReservada,
    String localizacao

) {
    
}
