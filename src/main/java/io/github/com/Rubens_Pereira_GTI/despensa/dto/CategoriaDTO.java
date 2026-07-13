package io.github.com.Rubens_Pereira_GTI.despensa.dto;

import io.github.com.Rubens_Pereira_GTI.despensa.entity.Categoria;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Local;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoriaDTO(
        Long id,

        @NotBlank
        String nome,

        String descricao,

        @NotNull
        Long localId

) {
    public Categoria toCategoria(){
        Categoria categoria = new Categoria();
        categoria.setId(this.id);
        categoria.setNome(this.nome);
        categoria.setDescricao(this.descricao);
        return categoria;
    }
}
