package io.github.com.Rubens_Pereira_GTI.despensa.controllers;

import io.github.com.Rubens_Pereira_GTI.despensa.entity.Produto;
import io.github.com.Rubens_Pereira_GTI.despensa.dto.ProdutoRequestDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.dto.ProdutoResponseDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService){
        this. produtoService = produtoService;
    }
    
    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> salvarProduto(@Valid @RequestBody ProdutoRequestDTO dto){
        ProdutoResponseDTO responseDTO = produtoService.salvarProduto(dto);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping
    public ResponseEntity<ProdutoResponseDTO> alterarProduto(@RequestBody ProdutoRequestDTO dto){
        if(dto.id() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID é obrigatório");
        }
        ProdutoResponseDTO responseDTO = produtoService.alterarProduto(dto);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> encontraProdutoPorId(@Valid @PathVariable Long id){
        ProdutoResponseDTO produto = produtoService.encontraProdutoPorId(id);
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> deletaProduto(@Valid @PathVariable Long id){
        ProdutoResponseDTO response = produtoService.deletaProduto(id);
        return ResponseEntity.ok(response);
    }








}
