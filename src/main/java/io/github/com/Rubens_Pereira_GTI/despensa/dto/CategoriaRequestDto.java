package io.github.com.Rubens_Pereira_GTI.despensa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoriaRequestDto(


        @NotBlank(message = "O nome da categoria é obrigatório.")
        String nome,

        String descricao,

        @NotNull(message = "O local é obrigatório.")
        Long localId
) {
}
