package io.github.com.Rubens_Pereira_GTI.despensa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record LocalDTO(

        Long id,

        @Size(max = 100)
        @NotBlank(message = "campo obrigatorio")
        String nome,

        @Size(max = 255)
        String descricao,

        @NotNull(message = "campo obrigatorio")
        Boolean ativo


) {
    
}
