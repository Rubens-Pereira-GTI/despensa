package io.github.com.Rubens_Pereira_GTI.despensa.controllers;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.CategoriaDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.dto.CategoriaResponseDto;
import io.github.com.Rubens_Pereira_GTI.despensa.dto.ErroResponse;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Categoria;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Local;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Produto;
import io.github.com.Rubens_Pereira_GTI.despensa.exception.OperacaoNaoPermitidaException;
import io.github.com.Rubens_Pereira_GTI.despensa.exception.RegistroDuplicadoException;
import io.github.com.Rubens_Pereira_GTI.despensa.service.CategoriaService;
import io.github.com.Rubens_Pereira_GTI.despensa.service.LocalService;
import io.github.com.Rubens_Pereira_GTI.despensa.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;
    private final LocalService localService;

    public CategoriaController(CategoriaService categoriaService, LocalService localService) {
        this.categoriaService = categoriaService;
        this.localService = localService;
    }

    @PostMapping
    public ResponseEntity<Object> salvaCategoria(@RequestBody @Valid CategoriaDTO dto) {

        Categoria categoria = dto.toCategoria();
        categoriaService.salvarCategoria(categoria);

        URI location = ServletUriComponentsBuilder.
                    fromCurrentRequest().
                    path("/{id}").
                    buildAndExpand(categoria.getId()).
                    toUri();

        return ResponseEntity.created(location).build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> buscaCategoriaPorId(@PathVariable Long id){

        Optional<Categoria> categoriaOpt = categoriaService.buscaCategoria(id);
        if(categoriaOpt.isEmpty()){
            return  ResponseEntity.notFound().build();
        }

        Categoria categoria = categoriaOpt.get();

        CategoriaDTO categoriaDTO = CategoriaDTO.toDTO(categoria);

        return ResponseEntity.ok(categoriaDTO);
    }

    @GetMapping
    ResponseEntity<List<CategoriaDTO>> buscarTodas(){

        List<Categoria> categorias = categoriaService.buscarTodas();

        List<CategoriaDTO> dtos = categorias.stream().map(CategoriaDTO::toDTO).toList();

        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{id}")
    ResponseEntity<Object> atualizaCategoria(@PathVariable Long id,
                                             @RequestBody @Valid CategoriaDTO dto){

        categoriaService.atualizarCategoria(dto, id);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{id}")
    ResponseEntity<Object> deletaCategoria(@PathVariable Long id){

        Optional<Categoria> categoriaOpt = categoriaService.buscaCategoriaPorId(id);
        if(categoriaOpt.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        try{
            categoriaService.deletar(categoriaOpt.get());
            return ResponseEntity.noContent().build();

        }catch (OperacaoNaoPermitidaException ex){
            ErroResponse erroResponse = ErroResponse.conflito(ex.getMessage());
            return ResponseEntity.status(erroResponse.status()).body(erroResponse);
        }

    }


}
