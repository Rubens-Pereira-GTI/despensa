package io.github.com.Rubens_Pereira_GTI.despensa.service;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.EstoqueDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Estoque;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Produto;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.EstoqueRepository;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EstoqueService {

    private final ProdutoRepository produtoRepository;
    private final EstoqueRepository estoqueRepository;
    

    public EstoqueService(EstoqueRepository estoqueRepository, ProdutoRepository produtoRepository){
        this.estoqueRepository = estoqueRepository;
        this.produtoRepository = produtoRepository;
    }


    public Estoque salvar(Estoque estoque){

        Optional<Produto> produtoOpt = produtoRepository.findById(estoque.getProdutoId());
        
        if(produtoOpt.isEmpty()){
            throw new EntityNotFoundException("Produto não encontrado");
        }
        
        
        estoque.setProduto(produtoOpt.get());
        return estoqueRepository.save(estoque);
    }


    @Transactional(readOnly = true)
    public Estoque buscarPorId(Long id) {
        Optional<Estoque> estoqueOpt = estoqueRepository.findById(id);
        if(estoqueOpt.isEmpty()){
            throw new EntityNotFoundException("Estoque não encontrado");
        }

        Produto produto = produtoRepository.findByEstoquesContaining(estoqueOpt.get())
            .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        Estoque estoque = estoqueOpt.get();
        estoque.setProduto(produto);
        
        return estoque;
    }

    public Page<Estoque> buscaPaginada(Integer page, Integer size){

        PageRequest pageRequest = PageRequest.of(page, size);

        return estoqueRepository.findAll(pageRequest);
        
    }

}
