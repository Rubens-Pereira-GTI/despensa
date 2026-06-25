package io.github.com.Rubens_Pereira_GTI.despensa.service;

import io.github.com.Rubens_Pereira_GTI.despensa.converter.ProdutoConverter;
import io.github.com.Rubens_Pereira_GTI.despensa.dto.ProdutoRequestDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.dto.ProdutoResponseDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Categoria;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Produto;
import io.github.com.Rubens_Pereira_GTI.despensa.converter.ProdutoDtoConverter;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.UnidadeMedida;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.CategoriaRepository;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    private final ProdutoRepository produtoRepository;
    private final ProdutoDtoConverter produtoDtoConverter;
    private final ProdutoConverter produtoConverter;

    @Autowired
    private ConversionService conversionService;

    public ProdutoService(ProdutoRepository produtoRepository, ProdutoDtoConverter produtoDtoConverter, ProdutoConverter produtoConverter){
        this.produtoRepository = produtoRepository;
        this.produtoDtoConverter = produtoDtoConverter;
        this.produtoConverter = produtoConverter;

    }

    public ProdutoResponseDTO encontraProdutoPorId(Long id)  {
        Produto produto = null;
        Optional<Produto> produtoOpt = produtoRepository.findById(id);

        if(produtoOpt.isPresent()){
            produto = produtoOpt.get();
        }else {
            throw  new RuntimeException("Produto não encontrado, id do produto: "+ id);
        }

        return produtoConverter.convert(produto);
    }

    public ProdutoResponseDTO salvarProduto(ProdutoRequestDTO produtoRequestDTO) {
        //@Valid na camada Controller vai verificar se o DTO é null
        Produto produto = produtoDtoConverter.convert(produtoRequestDTO);
        Optional<Categoria> categoriaOpt = categoriaRepository.findById(produto.getCategoriaId());
        Optional<UnidadeMedida> unidadeMedidaOpt; // TODO terminar
        if(categoriaOpt.isPresent()){
            produto.setCategoriaId(categoriaOpt.get().getId());
            produto = produtoRepository.save(produto);
        }else {
            throw  new RuntimeException("Categoria não encontrada, id do produto: "+ produto.getCategoriaId());
        }
        return produtoConverter.convert(produto);
    }


    public ProdutoResponseDTO alterarProduto(ProdutoRequestDTO dto) {
        Produto produto = produtoDtoConverter.convert(dto);
        Optional<Produto> produtoOpt = produtoRepository.findById(produto.getId());
        if(produtoOpt.isEmpty()){
            throw new RuntimeException("Produto não encontrado com o ID informado.");
        }
        produto = produtoRepository.save(produto);
        return produtoConverter.convert(produto);
    }
}
