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
import io.github.com.Rubens_Pereira_GTI.despensa.repository.UnidadeMedidaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService {


    private final ProdutoRepository produtoRepository;
    private final ProdutoDtoConverter produtoDtoConverter;
    private final ProdutoConverter produtoConverter;
    private final UnidadeMedidaRepository unidadeMedidaRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository produtoRepository,
                          ProdutoDtoConverter produtoDtoConverter,
                          ProdutoConverter produtoConverter,
                          CategoriaRepository categoriaRepository,
                          UnidadeMedidaRepository unidadeMedidaRepository){

        this.produtoRepository = produtoRepository;
        this.produtoDtoConverter = produtoDtoConverter;
        this.produtoConverter = produtoConverter;
        this.unidadeMedidaRepository = unidadeMedidaRepository;
        this.categoriaRepository = categoriaRepository;
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

        Produto produto = produtoDtoConverter.convert(produtoRequestDTO);

        //verifica se os relacionamentos existem
        Optional<Categoria> categoriaOpt = categoriaRepository.findById(produto.getCategoriaId());
        Optional<UnidadeMedida> unidadeMedidaOpt = unidadeMedidaRepository.findById(produto.getUnidadeMedidaId());
        if(categoriaOpt.isEmpty() ){
            throw  new RuntimeException("Categoria não encontrada: "+ produto.getCategoriaId());
        } else if (unidadeMedidaOpt.isEmpty()) {
            throw  new RuntimeException("Unidade de medida não encontrada: "+ produto.getUnidadeMedidaId());
        } else{
            produto = produtoRepository.save(produto);
        }
        return produtoConverter.convert(produto);
    }


    public ProdutoResponseDTO alterarProduto(ProdutoRequestDTO dto) {
        Produto produto = produtoDtoConverter.convert(dto);
        Optional<Produto> produtoOpt = produtoRepository.findById(produto.getId());
        Optional<Categoria> categoriaOpt = categoriaRepository.findById(produto.getCategoriaId());
        Optional<UnidadeMedida> unidadeMedidaOpt = unidadeMedidaRepository.findById(produto.getUnidadeMedidaId());

        //verifica se o produto existe
        if(produtoOpt.isEmpty()){
            throw new RuntimeException("Produto não encontrado com o ID informado.");
        }

        //verifica se os relacionametos existem
        if(unidadeMedidaOpt.isEmpty()){
            throw  new RuntimeException("Unidade de medida não encontrada: "+ produto.getUnidadeMedidaId());
        } else if (categoriaOpt.isEmpty()) {
            throw  new RuntimeException("Categoria não encontrada: "+ produto.getCategoriaId());
        }

        produto = produtoRepository.save(produto);
        return produtoConverter.convert(produto);
    }

    public Page<ProdutoResponseDTO> listarProdutos(Pageable pageable) {
        Page<Produto> produtosPage = produtoRepository.findAll(pageable);

        if (produtosPage.isEmpty()) {
            throw new RuntimeException("Página vazia");
        }

        return produtosPage.map(produtoConverter::convert);
    }

    public void deletaProduto(Long id) {
        //checa se o produto existe
        Optional<Produto>produtoOpt = produtoRepository.findById(id);
        if(produtoOpt.isPresent()) produtoRepository.deleteById(id);
    }
}
