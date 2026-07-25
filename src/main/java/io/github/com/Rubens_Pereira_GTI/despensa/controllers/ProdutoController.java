package io.github.com.Rubens_Pereira_GTI.despensa.controllers;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.ErroResponse;
import io.github.com.Rubens_Pereira_GTI.despensa.dto.ProdutoDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Categoria;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Produto;
import io.github.com.Rubens_Pereira_GTI.despensa.exception.RegistroDuplicadoException;
import io.github.com.Rubens_Pereira_GTI.despensa.service.CategoriaService;
import io.github.com.Rubens_Pereira_GTI.despensa.service.LocalService;
import io.github.com.Rubens_Pereira_GTI.despensa.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;
    private final CategoriaService categoriaService;
    private final LocalService localService;

    public ProdutoController(ProdutoService produtoService, CategoriaService categoriaService, LocalService localService){
        this.produtoService = produtoService;
        this.categoriaService = categoriaService;
        this.localService = localService;
    }

    @PostMapping
    public ResponseEntity<Object> salvarProduto(@Valid @RequestBody ProdutoDTO dto){

        Produto produto = dto.toProduto();
        //TODO categoriaId e local id não estão sendo persistidos
        Optional<Categoria> categoriaOpt = categoriaService.buscaCategoriaPorId(produto.getCategoriaId());
        //Optional<Local> localOpt = localService.buscarPorId(produto.getLocalId());

        produto.setCategoria(categoriaOpt.get());
        try{
            produtoService.salvarProduto(produto);

        }catch (RegistroDuplicadoException ex){
            ErroResponse erroResponse = ErroResponse.conflito(ex.getMessage());
            return ResponseEntity.status(erroResponse.status()).body(erroResponse);
        }


        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(produto.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> buscarProduto(@PathVariable Long id){
        Optional<Produto> produtoOpt = produtoService.buscarProduto(id);
        if(produtoOpt.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Produto produto = produtoOpt.get();
        ProdutoDTO dto = new ProdutoDTO(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getEstoqueMinimo(),
                produto.isAtivo(),
                produto.getCategoriaId(),
                produto.getLocalId(),
                produto.getLocalizacao()
        );
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarProduto(@Valid @RequestBody ProdutoDTO dto, @PathVariable Long id){

        Optional<Produto> produtoOpt = produtoService.buscarProduto(id);
        if(produtoOpt.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Produto produto = produtoOpt.get();
        produto.setNome(dto.nome());
        produto.setDescricao(dto.descricao());
        produto.setEstoqueMinimo(dto.estoqueMinimo());
        produto.setAtivo(dto.ativo());
        produto.setCategoriaId(dto.categoriaId());
        produto.setLocalId(dto.localId());
        produto.setLocalizacao(dto.localizacao());

        try {
            produtoService.atualizar(produto);

        }catch (RegistroDuplicadoException ex){
            ErroResponse erroResponse = ErroResponse.conflito(ex.getMessage());
            return ResponseEntity.status(erroResponse.status()).body(erroResponse);
        }

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaProduto(@PathVariable Long id){
        Optional<Produto> produtoOpt = produtoService.buscarProduto(id);
        if(produtoOpt.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        produtoService.deletar(produtoOpt.get());
        return ResponseEntity.noContent().build();
    }

}
