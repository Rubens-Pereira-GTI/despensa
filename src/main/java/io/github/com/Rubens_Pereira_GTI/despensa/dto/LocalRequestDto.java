package io.github.com.Rubens_Pereira_GTI.despensa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record LocalRequestDto(
        Long id,

        @NotBlank(message = "campo nome obrigatorio")
        String nome,

        String descricao,

        @NotNull(message = "campo ativo obrigatorio")
        Boolean ativo

        ) {
}
