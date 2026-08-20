package io.github.com.Rubens_Pereira_GTI.despensa.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;

public record ProdutoResponse(

    Long id,
    @NotBlank(message = "Nome é obrigatório")
    String nome,
    String descricao,
    @NotBlank(message = "Estoque mínimo é obrigatório")
    BigDecimal estoqueMinimo,
    Boolean ativo,
    CategoriaResumoDTO categoria,
    UnidadeMedidaDTO unidadeMedida, //TODO fazer dto de unidadeMedida e local
    String localizacao
    
) {
    
}
