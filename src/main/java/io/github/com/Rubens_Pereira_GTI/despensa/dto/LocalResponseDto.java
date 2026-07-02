package io.github.com.Rubens_Pereira_GTI.despensa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record LocalResponseDto(

        //TODO colocar Mesnagens nas validações
        @NotNull
        Long id,

        @NotNull
        @NotBlank
        String nome,

        @NotNull
        @NotBlank
        String descricao,

        @NotNull
        Boolean ativo,

        @NotNull
        LocalDateTime dataCriacao,

        @NotNull
        LocalDateTime dataAtualizacao
) {
}
