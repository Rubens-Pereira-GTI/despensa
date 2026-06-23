package io.github.com.Rubens_Pereira_GTI.despensa.service;

import io.github.com.Rubens_Pereira_GTI.despensa.entity.Produto;
import io.github.com.Rubens_Pereira_GTI.despensa.dto.ProdutoRequestDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.mappers.response.ProdutoResponseDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    public Produto encontraProdutoPorId(Long id)  {
        Produto produto = null;
        Optional<Produto> produtoOpt = produtoRepository.findById(id);

        if(produtoOpt.isPresent()){
            produto = produtoOpt.get();
        }else {
            throw  new RuntimeException("Produto não encontrado, id do produto: "+ id);
        }
        return produto;
    }

    /*
    public ProdutoResponseDTO criaProduto(@RequestBody ProdutoRequestDTO produtoRequestDTO) {

        // TODO converter o dto em produto

        ProdutoResponseDTO produtoResponseDTO = produtoRepository.save(produto);

        return produtoResponseDTO;
    }

     */
}
