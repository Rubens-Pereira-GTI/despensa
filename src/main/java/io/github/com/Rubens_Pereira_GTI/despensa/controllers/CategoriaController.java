package io.github.com.Rubens_Pereira_GTI.despensa.controllers;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.CategoriaDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.dto.CategoriaResponseDto;
import io.github.com.Rubens_Pereira_GTI.despensa.dto.ErroResponse;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Categoria;
import io.github.com.Rubens_Pereira_GTI.despensa.exception.RegistroDuplicadoException;
import io.github.com.Rubens_Pereira_GTI.despensa.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> buscaCategoriaPorId(@PathVariable Long id){

        Optional<Categoria> categoriaOpt = categoriaService.buscaCategoriaPorId(id);
        if(categoriaOpt.isEmpty()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Categoria categoria = categoriaOpt.get();

        CategoriaDTO categoriaDTO = new CategoriaDTO(
                categoria.getId(),
                categoria.getNome(),
                categoria.getDescricao(),
                categoria.getLocalId()
        );

        return ResponseEntity.ok(categoriaDTO);
    }

    @PostMapping
    public ResponseEntity<Object> salvaCategoria(@RequestBody @Valid CategoriaDTO dto) {

        Categoria categoria = dto.toCategoria();

        try {

            categoriaService.salvarCategoria(categoria);

            URI location = ServletUriComponentsBuilder.
                    fromCurrentRequest().
                    path("/{id}").
                    buildAndExpand(categoria.getId()).
                    toUri();

            return ResponseEntity.created(location).build();

        } catch (RegistroDuplicadoException ex){
            ErroResponse erroResponse = ErroResponse.conflito(ex.getMessage());
            return ResponseEntity.status(erroResponse.status()).body(erroResponse);

        }
    }

    @PutMapping("/{id}")
    ResponseEntity<Object> atualizaCategoria(@PathVariable Long id,
                                                           @RequestBody @Valid CategoriaDTO dto){

        Optional<Categoria> categoriaOpt = categoriaService.buscaCategoriaPorId(id);
        try {
            if (categoriaOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            Categoria categoria = categoriaOpt.get();
            categoria.setId(id);
            categoria.setNome(dto.nome());
            categoria.setDescricao(dto.descricao());
            categoria.setLocalId(dto.localId());

            categoriaService.atualizarCategoria(categoria);
            return ResponseEntity.noContent().build();

        }catch (RegistroDuplicadoException ex){
            ErroResponse errResponse = ErroResponse.conflito(ex.getMessage());
            return ResponseEntity.status(errResponse.status()).body(errResponse);
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletaCategoria(@PathVariable Long id){

        Optional<Categoria> categoriaOpt = categoriaService.buscaCategoriaPorId(id);
        if(categoriaOpt.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        categoriaService.deletar(categoriaOpt.get());

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<CategoriaResponseDto>> buscaTodasCategoriasPaginada(
            @PageableDefault(size = 10) Pageable pageable) {
        Page<CategoriaResponseDto> responseDto = categoriaService.buscaTodasCategoriasPaginada(pageable);
        return ResponseEntity.ok(responseDto);
    }

}
