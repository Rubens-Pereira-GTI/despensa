package io.github.com.Rubens_Pereira_GTI.despensa.mapper;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.ProdutoDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Categoria;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Local;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Produto;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.UnidadeMedida;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.CategoriaRepository;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.LocalRepository;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.UnidadeMedidaRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

    private final CategoriaRepository categoriaRepository;
    private final UnidadeMedidaRepository unidadeMedidaRepository;
    

    public ProdutoMapper(CategoriaRepository categoriaRepository, UnidadeMedidaRepository unidadeMedidaRepository){
        this.categoriaRepository = categoriaRepository;
        this.unidadeMedidaRepository = unidadeMedidaRepository;
    }

    public Produto toEntity(ProdutoDTO dto) {
        if (dto == null) {
            return null;
        }

        Optional<Categoria> categoriaOpt = categoriaRepository.findById(dto.categoriaId());
        if(categoriaOpt.isEmpty()) throw new EntityNotFoundException("Categoria não encontrada");
        Categoria categoria = categoriaOpt.get();

        Optional<UnidadeMedida> unidadeMedidaOpt = unidadeMedidaRepository.findById(dto.unidadeMedidaId());
        if(unidadeMedidaOpt.isEmpty()) throw new EntityNotFoundException("Unidade de medida não encontrada");
        UnidadeMedida unidadeMedida = unidadeMedidaOpt.get();

        Produto produto = new Produto();
        produto.setId(dto.id());
        produto.setNome(dto.nome());
        produto.setDescricao(dto.descricao());
        produto.setEstoqueMinimo(dto.estoqueMinimo());
        if (dto.ativo() != null) {
            produto.setAtivo(dto.ativo());
        }
        produto.setUnidadeMedida(unidadeMedida);
        produto.setCategoriaId(dto.categoriaId());
        produto.setCategoria(categoria);
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
                entity.getUnidadeMedidaId(),
                entity.getLocalizacao(),
                entity.getDataDeCriacao(),
                entity.getDataAtualizacao()
        );
    }
}
