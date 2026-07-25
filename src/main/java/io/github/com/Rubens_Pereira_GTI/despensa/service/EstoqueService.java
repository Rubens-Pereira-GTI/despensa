package io.github.com.Rubens_Pereira_GTI.despensa.service;

import io.github.com.Rubens_Pereira_GTI.despensa.entity.Estoque;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.EstoqueRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;

    public EstoqueService(EstoqueRepository estoqueRepository){
        this.estoqueRepository = estoqueRepository;
    }


    @Transactional
    public Optional<Estoque> findEstoque(Long id) {
        Optional<Estoque> estoqueOpt = estoqueRepository.findById(id);
        if(estoqueOpt.isEmpty()){
            return estoqueOpt;
        }
        Long produtoId = estoqueOpt.get().getProduto().getId();
        String nomeProduto = estoqueOpt.get().getProduto().getNome();
        estoqueOpt.get().setProdutoId(produtoId);
        estoqueOpt.get().setNomeProduto(nomeProduto);
        return estoqueOpt;
    }
}
