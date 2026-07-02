package io.github.com.Rubens_Pereira_GTI.despensa.converter;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.LocalResponseDto;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Local;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Produto;
import org.springframework.core.convert.converter.Converter;

public class LocalDtoConverter implements Converter<Local, LocalResponseDto> {

    @Override
    public LocalResponseDto convert(Local local){

        LocalResponseDto responseDto = new LocalResponseDto(
                local.getId(),
                local.getNome(),
                local.getDescricao(),
                local.getAtivo(),
                local.getDataCriacao(),
                local.getDataAtualizacao()

        );
        return  responseDto;
    }
}
