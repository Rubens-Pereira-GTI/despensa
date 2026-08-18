package io.github.com.Rubens_Pereira_GTI.despensa.service;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.ProdutoDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Categoria;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Produto;
import io.github.com.Rubens_Pereira_GTI.despensa.exception.OperacaoNaoPermitidaException;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.CategoriaRepository;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.ProdutoRepository;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.UnidadeMedidaRepository;
import io.github.com.Rubens_Pereira_GTI.despensa.validator.ProdutoValidator;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProdutoService {


    private final ProdutoRepository produtoRepository;
    private final UnidadeMedidaRepository unidadeMedidaRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProdutoValidator produtoValidator;

    public ProdutoService(ProdutoRepository produtoRepository,
                          CategoriaRepository categoriaRepository,
                          UnidadeMedidaRepository unidadeMedidaRepository,
                          ProdutoValidator produtoValidator){

        this.produtoRepository = produtoRepository;
        this.unidadeMedidaRepository = unidadeMedidaRepository;
        this.categoriaRepository = categoriaRepository;
        this.produtoValidator = produtoValidator;
    }

    public Produto salvarProduto(Produto produto){
        produtoValidator.validar(produto);
        return produtoRepository.save(produto);
    }


    @Transactional
    public Optional<Produto> buscarProduto(Long id) {
        Optional<Produto> produtoOpt = produtoRepository.findById(id);
        //TODO colocar o EntityNotFoundException na classe global
        if(produtoOpt.isEmpty()) throw new EntityNotFoundException("Produto não encontrado");
        

        return produtoOpt;
    }

    public Produto atualizar(Produto produto) {
        produtoValidator.validar(produto);
        return produtoRepository.save(produto);
    }

    public void deletar(Produto produto) {
        produto.setAtivo(false);
        produtoRepository.save(produto);
    }



}
