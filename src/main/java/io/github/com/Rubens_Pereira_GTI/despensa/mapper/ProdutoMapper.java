package io.github.com.Rubens_Pereira_GTI.despensa.mapper;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.ProdutoDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.dto.ProdutoResponse;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Produto;


import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

    private final CategoriaMapper categoriaMapper;
    private final UnidadeMedidaMapper unidadeMedidaMapper;

    public ProdutoMapper(CategoriaMapper categoriaMapper, UnidadeMedidaMapper unidadeMedidaMapper) {
        this.categoriaMapper = categoriaMapper;
        this.unidadeMedidaMapper = unidadeMedidaMapper;
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
            categoriaMapper.toResumoDTO(produto.getCategoria()),
            unidadeMedidaMapper.toDTO(produto.getUnidadeMedida()),
            produto.getLocalizacao()
        );
    }
}
