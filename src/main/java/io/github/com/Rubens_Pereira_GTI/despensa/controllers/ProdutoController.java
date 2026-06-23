package io.github.com.Rubens_Pereira_GTI.despensa.controllers;

import io.github.com.Rubens_Pereira_GTI.despensa.entity.Produto;
import io.github.com.Rubens_Pereira_GTI.despensa.dto.ProdutoRequestDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.mappers.response.ProdutoResponseDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService){
        this. produtoService = produtoService;
    }

    @PostMapping
    public  ResponseEntity<ProdutoResponseDTO> criaProduto(@RequestBody ProdutoRequestDTO produtoRequestDTO){
       ProdutoResponseDTO produtoResponseDTO = produtoService.criaProduto(produtoRequestDTO);
        return ResponseEntity.ok(produtoResponseDTO);
    }



    @GetMapping
    public ResponseEntity<Produto> encontraProdutoPorId(@Valid @PathVariable Long id){
        Produto produto = produtoService.encontraProdutoPorId(id);
        return ResponseEntity.ok(produto);
    }








}
