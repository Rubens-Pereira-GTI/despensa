package io.github.com.Rubens_Pereira_GTI.despensa.controllers;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.ProdutoRequestDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.dto.ProdutoResponseDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.service.ProdutoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService){
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> salvarProduto(@Valid @RequestBody ProdutoRequestDTO dto){
        ProdutoResponseDTO responseDTO = produtoService.salvarProduto(dto);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping
    public ResponseEntity<ProdutoResponseDTO> alterarProduto(@Valid @RequestBody ProdutoRequestDTO dto){
        ProdutoResponseDTO responseDTO = produtoService.alterarProduto(dto);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> encontraProdutoPorId(@PathVariable Long id){
        ProdutoResponseDTO produto = produtoService.encontraProdutoPorId(id);
        return ResponseEntity.ok(produto);
    }

    @GetMapping("/paginado")
    public ResponseEntity<Page<ProdutoResponseDTO>> listarProdutosPaginado(
            @PageableDefault(size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<ProdutoResponseDTO> produtos = produtoService.listarProdutos(pageable);
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/TodosProdutos")
    public ResponseEntity<List<ProdutoResponseDTO>> todosProdutos(){
        List<ProdutoResponseDTO> produtos = produtoService.listarTodosProdutos();
        return ResponseEntity.ok(produtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaProduto(@PathVariable Long id){
        produtoService.deletaProduto(id);
        return ResponseEntity.noContent().build();
    }
}
