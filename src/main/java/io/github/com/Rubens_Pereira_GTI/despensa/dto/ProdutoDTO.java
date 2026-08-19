package io.github.com.Rubens_Pereira_GTI.despensa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProdutoDTO(
        
        @NotBlank(message = "campo nome obrigatório")
        @Size(max = 100)
        String nome,

        @Size(max = 255)
        String descricao,

        @NotNull(message = "campo estoque mínimo obrigatório")
        BigDecimal estoqueMinimo,
        
        @NotNull(message = "campo ativo obrigatório")
        Boolean ativo,

        @NotNull(message = "campo categoria obrigatório")
        Long categoriaId,
        
        @NotNull(message = "campo local obrigatório")
        Long localId,

        @NotNull(message = "campo unidade de medida obrigatório")
        Long unidadeMedidaId,

        String localizacao

) {

}
