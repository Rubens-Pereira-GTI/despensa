package io.github.com.Rubens_Pereira_GTI.despensa.dto;

import java.time.LocalDateTime;

public record LocalRequestDto(
        Long id,

        String nome,

        String descricao,

        Boolean ativo,

        LocalDateTime dataCriacao,

        LocalDateTime dataAtualizacao

        ) {
}
