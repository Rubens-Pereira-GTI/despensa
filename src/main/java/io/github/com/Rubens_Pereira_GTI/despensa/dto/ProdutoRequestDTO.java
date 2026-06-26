package io.github.com.Rubens_Pereira_GTI.despensa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record ProdutoRequestDTO(

        Long id,

        @NotBlank(message = "O nome do produto é obrigatório.")
        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres.")
        String nome,

        String descricao,

        @NotNull(message = "O estoque mínimo deve ser informado.")
        @PositiveOrZero(message = "O estoque mínimo não pode ser negativo.")
        BigDecimal estoqueMinimo,

        boolean ativo,

        @NotNull(message = "A categoria é obrigatória.")
        Long categoriaId,

        @NotNull(message = "A unidade de medida é obrigatória.")
        Long unidadeMedidaId,

        // Incluindo os campos transientes que sua API parece receber/tratar
        Long localId,
        String localizacao
) {
}
