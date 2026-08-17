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
        Long localId

) {
    public Categoria toCategoria(){
        Categoria categoria = new Categoria();
        categoria.setId(this.id);
        categoria.setNome(this.nome);
        categoria.setDescricao(this.descricao);
        categoria.setLocalId(this.localId);
        return categoria;
    }

    public static CategoriaDTO toDTO(Categoria categoria){
        return new CategoriaDTO(
                categoria.getId(),
                categoria.getNome(),
                categoria.getDescricao(),
                categoria.getLocalId());
    }
}
