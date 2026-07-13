package io.github.com.Rubens_Pereira_GTI.despensa.converter;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.CategoriaResponseDto;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Categoria;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Local;
import org.springframework.stereotype.Component;

@Component
public class CategoriaConverter {

    public Categoria toEntity(CategoriaRequestDto dto, Local local) {
        Categoria categoria = new Categoria();
        categoria.setNome(dto.nome());
        categoria.setDescricao(dto.descricao());
        categoria.setLocal(local);
        return categoria;
    }

    public CategoriaResponseDto toResponseDto(Categoria categoria) {
        return new CategoriaResponseDto(
                categoria.getId(),
                categoria.getNome(),
                categoria.getDescricao(),
                categoria.getLocal() != null ? categoria.getLocal().getId() : null
        );
    }
}
