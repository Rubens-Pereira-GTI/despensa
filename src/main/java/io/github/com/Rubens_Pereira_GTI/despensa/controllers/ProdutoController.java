package io.github.com.Rubens_Pereira_GTI.despensa.controllers;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.ErroResponse;
import io.github.com.Rubens_Pereira_GTI.despensa.dto.ProdutoDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.dto.ProdutoResponse;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Categoria;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Produto;
import io.github.com.Rubens_Pereira_GTI.despensa.exception.RegistroDuplicadoException;
import io.github.com.Rubens_Pereira_GTI.despensa.mapper.ProdutoMapper;
import io.github.com.Rubens_Pereira_GTI.despensa.service.CategoriaService;
import io.github.com.Rubens_Pereira_GTI.despensa.service.ProdutoService;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;
    private final ProdutoMapper produtoMapper;

    public ProdutoController(ProdutoService produtoService, ProdutoMapper produtoMapper){
        this.produtoService = produtoService;
        this.produtoMapper = produtoMapper;
    }

    @PostMapping
    public ResponseEntity<Object> salvarProduto(@Valid @RequestBody ProdutoDTO dto){

        Produto produto = produtoMapper.toEntity(dto);
        produto = produtoService.salvarProduto(produto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(produto.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> buscarProduto(@PathVariable Long id){
        Produto produto =produtoService.buscarProduto(id);
        ProdutoResponse produtoResponse = produtoMapper.toProdutoResponse(produto);
        return ResponseEntity.ok(produtoResponse);
    }
    
    @GetMapping
    public ResponseEntity<ProdutoResponse> buscasTodos(@RequestParam Integer page, 
                                                        @RequestParam Integer size,
                                                        @RequestParam String sort)
    {
        


        return ResponseEntity.ok(null);
    }
    

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarProduto(@Valid @RequestBody ProdutoDTO dto, @PathVariable Long id){

        Produto entity = produtoMapper.toEntity(dto);
        produtoService.atualizar(entity, id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaProduto(@PathVariable Long id){
        Produto produto = produtoService.buscarProduto(id);
        
        produtoService.deletar(produto);
        return ResponseEntity.noContent().build();
    }

}
