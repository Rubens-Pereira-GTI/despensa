package io.github.com.Rubens_Pereira_GTI.despensa.mapper;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.ProdutoDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.dto.ProdutoResponse;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Produto;


import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

    private final CategoriaMapper categoriaMapper;

    public ProdutoMapper(CategoriaMapper categoriaMapper) {
        this.categoriaMapper = categoriaMapper;
    }

    public Produto toEntity(ProdutoDTO dto) {
        if (dto == null) {
            return null;
        }

        Produto produto = new Produto();
        produto.setNome(dto.nome());
        produto.setDescricao(dto.descricao());
        produto.setEstoqueMinimo(dto.estoqueMinimo());
        if (dto.ativo() != null) {
            produto.setAtivo(dto.ativo());
        }
        produto.setCategoriaId(dto.categoriaId());
        produto.setLocalId(dto.localId());
        produto.setUnidadeMedidaId(dto.unidadeMedidaId());
        produto.setLocalizacao(dto.localizacao());


        return produto;
    }

    public ProdutoDTO toDTO(Produto entity) {
        if (entity == null) {
            return null;
        }

        return new ProdutoDTO(
                entity.getNome(),
                entity.getDescricao(),
                entity.getEstoqueMinimo(),
                entity.isAtivo(),
                entity.getCategoriaId(),
                entity.getLocalId(),
                entity.getUnidadeMedidaId(),
                entity.getLocalizacao()
        );
    }

    public ProdutoResponse toProdutoResponse(Produto produto){
        return new ProdutoResponse(
            produto.getId(),
            produto.getNome(),
            produto.getDescricao(),
            produto.getEstoqueMinimo(),
            produto.isAtivo(),
            categoriaMapper.toDTO(produto.getCategoria()),
            produto.getUnidadeMedida(),
            produto.getLocalizacao(),
            produto.getDataDeCriacao(),
            produto.getDataAtualizacao()
        );
    }
}
