package io.github.com.Rubens_Pereira_GTI.despensa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LocalRequestDto(

        @NotNull(groups = OnUpdate.class)
        Long id,

        @NotNull
        @NotBlank(message = "campo nome obrigatorio")
        String nome,

        String descricao,

        @NotNull(message = "campo ativo obrigatorio")
        Boolean ativo

        /*
        @NotNull
        LocalDateTime dataCriacao,

        @NotNull
        LocalDateTime dataAtualizacao

        */
        ) {
        public interface OnUpdate {}
}
