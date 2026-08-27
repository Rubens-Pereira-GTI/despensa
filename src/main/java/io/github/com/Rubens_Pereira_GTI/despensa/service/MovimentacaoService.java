package io.github.com.Rubens_Pereira_GTI.despensa.service;

import org.springframework.stereotype.Service;

import io.github.com.Rubens_Pereira_GTI.despensa.repository.MovimentacaoRepository;

@Service
public class MovimentacaoService {

    private final MovimentacaoRepository movimentacaoRepository;

    public MovimentacaoService(MovimentacaoRepository movimentacaoRepository){
        this.movimentacaoRepository = movimentacaoRepository;
    }

    
    
}
