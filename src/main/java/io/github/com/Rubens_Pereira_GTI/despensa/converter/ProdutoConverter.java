package io.github.com.Rubens_Pereira_GTI.despensa.converter;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.ProdutoResponseDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Produto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProdutoConverter implements Converter<Produto, ProdutoResponseDTO> {

    @Override
    public ProdutoResponseDTO convert(Produto source) {

        if (source == null) {
            return null;
        }

        ProdutoResponseDTO responseDTO = new ProdutoResponseDTO(
                source.getId(),
                source.getNome(),
                source.getDescricao(),
                source.getEstoqueMinimo(),
                source.isAtivo(),
                source.getDataDeCriacao(),
                source.getDataDeAtualizacao(),

                // Lendo do relacionamento JPA com segurança contra nulos:
                source.getCategoria() != null ? source.getCategoria().getId() : null,
                source.getUnidadeMedida() != null ? source.getUnidadeMedida().getId() : null,


                source.getLocalId(),
                source.getLocalizacao()
        );

        return responseDTO;
    }
}
