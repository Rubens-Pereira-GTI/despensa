package io.github.com.Rubens_Pereira_GTI.despensa.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.MovimentacaoHistoricoResponse;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Movimentacao;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.MovimentacaoRepository;

@Service
public class MovimentacaoService {

    private final MovimentacaoRepository movimentacaoRepository;

    public MovimentacaoService(MovimentacaoRepository movimentacaoRepository){
        this.movimentacaoRepository = movimentacaoRepository;
    }

    // listar os produtos a partir do local informado
    //Realizar a movimentação
    //saida: nada
    public void movimentar(Movimentacao mov){
        
    }

	public Page<MovimentacaoHistoricoResponse> buscaFiltrada(
        Integer page,
        Integer size,
        String orderBy,
        String direction,
        Long localId) {

            PageRequest pageRequest = PageRequest.of(page, size);            

		return movimentacaoRepository.findAllResponse(localId, pageRequest);
	}


    
}
