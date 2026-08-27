package io.github.com.Rubens_Pereira_GTI.despensa.mapper;

import org.springframework.stereotype.Component;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.MovimentacaoDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Movimentacao;

@Component
public class MovimentacaoMapper {
    
    public Movimentacao toEntity(MovimentacaoDTO dto){
        Movimentacao movimentacao = new Movimentacao();
        //movimentacao.setProdutoId(dto.produtoId());
        //movimentacao.set
        return movimentacao;
    }
}
