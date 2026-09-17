package io.github.com.Rubens_Pereira_GTI.despensa.mapper;

import org.springframework.stereotype.Component;
import io.github.com.Rubens_Pereira_GTI.despensa.dto.MovimentacaoDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.dto.MovimentacaoResponseDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Movimentacao;

@Component
public class MovimentacaoMapper {
    
    private final ProdutoMapper produtoMapper;

    MovimentacaoMapper(ProdutoMapper produtoMapper) {
        this.produtoMapper = produtoMapper;
    }

    public Movimentacao toEntity(MovimentacaoDTO dto){
        Movimentacao movimentacao = new Movimentacao();
        //movimentacao.setProdutoId(dto.produtoId());
        //movimentacao.set
        return movimentacao;
    }

    public MovimentacaoResponseDTO tResponseDTO (Movimentacao movimentacao){
        return new MovimentacaoResponseDTO(
            movimentacao.getId(),
            produtoMapper.toResumoDTO(movimentacao.getProduto()),
            movimentacao.getTipoMovimentacao(),
            movimentacao.getQuantidade(),
            movimentacao.getQtdAnterior(),
            movimentacao.getQtdNova(),
            movimentacao.getMotivo(),
            movimentacao.getDataMovimentacao()
            

        );
    }
}
