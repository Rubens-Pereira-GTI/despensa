package io.github.com.Rubens_Pereira_GTI.despensa.converter;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.ProdutoResponseDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Produto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProdutoConverter implements Converter<Produto, ProdutoResponseDTO> {

    @Override
    public ProdutoResponseDTO convert(Produto source) {

        ProdutoResponseDTO responseDTO = new ProdutoResponseDTO(
                source.getId(),
                source.getNome(),
                source.getDescricao(),
                source.getEstoqueMinimo(),
                source.isAtivo(),
                source.getDataDeCriacao(),
                source.getDataDeAtualizacao(),
                source.getCategoriaId() ,
                source.getUnidadeMedidaId(),
                source.getLocalId(),
                source.getLocalizacao()
        );

        return responseDTO;
    }


}
