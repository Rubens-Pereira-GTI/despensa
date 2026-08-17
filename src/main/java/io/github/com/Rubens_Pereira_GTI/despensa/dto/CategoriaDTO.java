package io.github.com.Rubens_Pereira_GTI.despensa.dto;

import io.github.com.Rubens_Pereira_GTI.despensa.entity.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CategoriaDTO(
        Long id,

        @Size(max = 100)
        @NotBlank
        String nome,

        @Size(max = 255)
        String descricao,

        @NotNull
        Long localId,

        @NotNull
        Boolean ativo

) {
   

}
