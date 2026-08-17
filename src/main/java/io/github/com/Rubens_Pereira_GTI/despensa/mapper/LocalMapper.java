package io.github.com.Rubens_Pereira_GTI.despensa.mapper;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.LocalDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Local;
import org.springframework.stereotype.Component;

@Component
public class LocalMapper {

    public Local toEntity(LocalDTO dto) {
        if (dto == null) {
            return null;
        }
        Local local = new Local();
        local.setId(dto.id());
        local.setNome(dto.nome());
        local.setDescricao(dto.descricao());
        local.setAtivo(dto.ativo());
        return local;
    }

    public LocalDTO toDTO(Local entity) {
        if (entity == null) {
            return null;
        }
        return new LocalDTO(
                entity.getId(),
                entity.getNome(),
                entity.getDescricao(),
                entity.getAtivo(),
                entity.getDataCriacao(),
                entity.getDataAtualizacao()
        );
    }

}
