package io.github.com.Rubens_Pereira_GTI.despensa.mapper;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.ProdutoDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

    public Produto toEntity(ProdutoDTO dto) {
        if (dto == null) {
            return null;
        }

        Produto produto = new Produto();
        produto.setId(dto.id());
        produto.setNome(dto.nome());
        produto.setDescricao(dto.descricao());
        produto.setEstoqueMinimo(dto.estoqueMinimo());
        if (dto.ativo() != null) {
            produto.setAtivo(dto.ativo());
        }
        produto.setCategoriaId(dto.categoriaId());
        produto.setLocalId(dto.localId());
        produto.setLocalizacao(dto.localizacao());
        produto.setDataDeCriacao(dto.dataCriacao());
        produto.setDataAtualizacao(dto.dataAtualizacao());

        return produto;
    }

    public ProdutoDTO toDTO(Produto entity) {
        if (entity == null) {
            return null;
        }

        return new ProdutoDTO(
                entity.getId(),
                entity.getNome(),
                entity.getDescricao(),
                entity.getEstoqueMinimo(),
                entity.isAtivo(),
                entity.getCategoriaId() != null ? entity.getCategoriaId() : (entity.getCategoria() != null ? entity.getCategoria().getId() : null),
                entity.getLocalId(),
                entity.getLocalizacao(),
                entity.getDataDeCriacao(),
                entity.getDataAtualizacao()
        );
    }
}
