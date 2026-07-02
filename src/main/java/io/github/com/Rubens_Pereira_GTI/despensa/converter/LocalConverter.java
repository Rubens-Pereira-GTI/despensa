package io.github.com.Rubens_Pereira_GTI.despensa.converter;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.LocalResponseDto;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LocalConverter implements Converter<Local, LocalResponseDto> {


    public LocalResponseDto convert(Local local){
        //TODO fazer verificaçao ocm objetos nulos
        LocalResponseDto dto = new LocalResponseDto(
                local.getId(),
                local.getNome(),
                local.getDescricao(),
                local.getAtivo(),
                local.getDataCriacao(),
                local.getDataAtualizacao()
        );

        return dto;
    }
}
