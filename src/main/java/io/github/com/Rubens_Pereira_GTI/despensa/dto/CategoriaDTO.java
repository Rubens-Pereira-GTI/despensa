package io.github.com.Rubens_Pereira_GTI.despensa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CategoriaDTO(
        Long id,

        @Size(max = 100)
        @NotBlank(message = "Nome é obrigatório.")
        String nome,

        @Size(max = 255)
        String descricao,

        @NotNull(message = "Local é obrigatório")
        Long localId,

        @NotNull
        Boolean ativo

) {
   

}
