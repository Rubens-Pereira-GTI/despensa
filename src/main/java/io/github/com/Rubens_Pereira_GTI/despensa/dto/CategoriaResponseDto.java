package io.github.com.Rubens_Pereira_GTI.despensa.dto;

public record CategoriaResponseDto(

        Long id,

        String nome,

        String descricao,

        LocalDTO localDTO,

        Boolean ativo
) {
}
