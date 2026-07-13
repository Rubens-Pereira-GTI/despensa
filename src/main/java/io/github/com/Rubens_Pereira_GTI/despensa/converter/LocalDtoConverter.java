package io.github.com.Rubens_Pereira_GTI.despensa.converter;

import io.github.com.Rubens_Pereira_GTI.despensa.entity.Local;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LocalDtoConverter implements Converter<LocalRequestDto, Local> {

    @Override
    public Local convert(LocalRequestDto requestDto){

        //TODO talvez mudar para o construtor, pra ficar mais limpo
        Local local = new Local();

        local.setId(requestDto.id());
        local.setNome(requestDto.nome());
        local.setDescricao(requestDto.descricao());
        local.setAtivo(requestDto.ativo());
        //local.setDataCriacao(requestDto.dataCriacao());
        //local.setDataAtualizacao(requestDto.dataAtualizacao());


        return  local;
    }
}
