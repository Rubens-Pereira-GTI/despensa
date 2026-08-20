package io.github.com.Rubens_Pereira_GTI.despensa.mapper;

import org.springframework.stereotype.Component;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.UnidadeMedidaDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.UnidadeMedida;

@Component
public class UnidadeMedidaMapper {
    
    public UnidadeMedida toEntity(UnidadeMedidaDTO dto) {        
        UnidadeMedida unidadeMedida = new UnidadeMedida();
        unidadeMedida.setId(dto.id());
        unidadeMedida.setNome(dto.nome());
        unidadeMedida.setSigla(dto.sigla());
        return unidadeMedida;
    }

    public UnidadeMedidaDTO toDTO(UnidadeMedida entity) {
        if (entity == null) {
            return null;
        }
        return new UnidadeMedidaDTO(
                entity.getId(),
                entity.getNome(),
                entity.getSigla()
        );
    }

}
