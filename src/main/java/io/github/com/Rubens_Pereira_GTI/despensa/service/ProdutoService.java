package io.github.com.Rubens_Pereira_GTI.despensa.service;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.ProdutoDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Categoria;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Produto;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.UnidadeMedida;
import io.github.com.Rubens_Pereira_GTI.despensa.exception.OperacaoNaoPermitidaException;
import io.github.com.Rubens_Pereira_GTI.despensa.mapper.ProdutoMapper;
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


    private final ProdutoMapper produtoMapper;
    private final ProdutoRepository produtoRepository;
    private final UnidadeMedidaRepository unidadeMedidaRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProdutoValidator produtoValidator;

    public ProdutoService(ProdutoRepository produtoRepository,
                          CategoriaRepository categoriaRepository,
                          UnidadeMedidaRepository unidadeMedidaRepository,
                          ProdutoValidator produtoValidator, 
                          ProdutoMapper produtoMapper){

        this.produtoRepository = produtoRepository;
        this.unidadeMedidaRepository = unidadeMedidaRepository;
        this.categoriaRepository = categoriaRepository;
        this.produtoValidator = produtoValidator;
        this.produtoMapper = produtoMapper;
    }

    public Produto salvarProduto(Produto produto){        

        Categoria categoria = categoriaRepository.findById(produto.getCategoriaId()).orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));
        produto.setCategoria(categoria);
        
        UnidadeMedida unidadeMedida = unidadeMedidaRepository.findById(produto.getUnidadeMedidaId()).orElseThrow(() -> new EntityNotFoundException("Unidade de medida não encontrada"));
        produto.setUnidadeMedida(unidadeMedida);
        
        produtoValidator.validar(produto);
        
        return produtoRepository.save(produto);
    }


    @Transactional
    public Produto buscarProduto(Long id) {
        Optional<Produto> produtoOpt = produtoRepository.findById(id);
        //TODO colocar o EntityNotFoundException na classe global
        if(produtoOpt.isEmpty()) throw new EntityNotFoundException("Produto não encontrado");
        
        Produto produto = produtoOpt.get();
        produto.setCategoriaId(produto.getCategoria().getId());
        produto.setUnidadeMedidaId(produto.getUnidadeMedida().getId());

        return produtoOpt.get();
    }

    @Transactional
    public Produto atualizar(Produto proAtualizado, Long id) {

        Produto produto = buscarProduto(id);

        produto.setNome(proAtualizado.getNome());
        produto.setDescricao(proAtualizado.getDescricao());
        produto.setEstoqueMinimo(proAtualizado.getEstoqueMinimo());
        produto.setAtivo(proAtualizado.isAtivo());
        produto.setLocalizacao(proAtualizado.getLocalizacao());

        Optional<Categoria> categoriaOpt = categoriaRepository.findById(proAtualizado.getCategoriaId());
        produto.setCategoria(categoriaOpt.get());
        
        Optional<UnidadeMedida> unidadeMedidaOpt = unidadeMedidaRepository.findById(proAtualizado.getUnidadeMedidaId());
        produto.setUnidadeMedida(unidadeMedidaOpt.get());

        produtoValidator.validar(produto);
        return produtoRepository.save(produto);
    }

    public void deletar(Produto produto) {
        produto.setAtivo(false);
        produtoRepository.save(produto);
    }



}
