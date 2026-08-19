package io.github.com.Rubens_Pereira_GTI.despensa.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.github.com.Rubens_Pereira_GTI.despensa.entity.UnidadeMedida;

public record ProdutoResponse(

    Long id,
    String nome,
    String descricao,
    BigDecimal estoqueMinimo,
    Boolean ativo,
    CategoriaDTO categoria,
    UnidadeMedida unidadeMedida, //TODO fazer dto de unidadeMedida e local
    String localizacao,
    LocalDateTime dataDeCriacao,
    LocalDateTime dataAtualizacao
    
) {
    
}
