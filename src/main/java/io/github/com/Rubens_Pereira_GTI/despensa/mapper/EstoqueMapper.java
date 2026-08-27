package io.github.com.Rubens_Pereira_GTI.despensa.mapper;

import org.springframework.stereotype.Component;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.EstoqueDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.dto.EstoqueResponseDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Estoque;

@Component
public class EstoqueMapper {

    private final ProdutoMapper produtoMapper;

    EstoqueMapper(ProdutoMapper produtoMapper) {
        this.produtoMapper = produtoMapper;
    }

    public EstoqueDTO toDTO(Estoque estoque){
        return new EstoqueDTO(
                estoque.getId(),
                estoque.getProdutoId(),
                estoque.getQuantidade(),
                estoque.getQtdReservada(),
                estoque.getLocalizacao(),
                estoque.getDataValidade()
                
        );
    }

    public Estoque toEstoque(EstoqueDTO dto){
        Estoque estoque = new Estoque();
        estoque.setProdutoId(dto.produtoId());
        estoque.setQuantidade(dto.quantidade());
        estoque.setQtdReservada(dto.qtdReservada());
        estoque.setLocalizacao(dto.localizacao());
        estoque.setDataValidade(dto.dataValidade());
        return estoque;
    }

    public EstoqueResponseDTO toResponseDTO(Estoque estoque){
        return new EstoqueResponseDTO(
                estoque.getId(),
                produtoMapper.toResumoDTO(estoque.getProduto()),
                estoque.getQuantidade(),
                estoque.getQtdReservada(),
                estoque.getLocalizacao()
        );
    }
}
