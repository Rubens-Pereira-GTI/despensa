package io.github.com.Rubens_Pereira_GTI.despensa.service;

import io.github.com.Rubens_Pereira_GTI.despensa.mapper.MovimentacaoMapper;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.EstoqueRepository;
import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import io.github.com.Rubens_Pereira_GTI.despensa.dto.MovimentacaoDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.dto.MovimentacaoHistoricoResponse;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Estoque;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Movimentacao;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Produto;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.TipoMovimentacao;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.MovimentacaoRepository;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class MovimentacaoService {

    private final MovimentacaoMapper movimentacaoMapper;
    private final EstoqueRepository estoqueRepository;
    private final MovimentacaoRepository movimentacaoRepository;
    private final ProdutoRepository produtoRepository;  

    public MovimentacaoService(MovimentacaoRepository movimentacaoRepository, EstoqueRepository estoqueRepository, MovimentacaoMapper movimentacaoMapper, ProdutoRepository produtoRepository){
        this.movimentacaoRepository = movimentacaoRepository;
        this.estoqueRepository = estoqueRepository;
        this.movimentacaoMapper = movimentacaoMapper;
        this.produtoRepository = produtoRepository;
    }

    // listar os produtos a partir do local informado
    //Realizar a movimentação
    //saida: nada
    public void movimentar(MovimentacaoDTO dto){

        
        
    }

	


    
}
