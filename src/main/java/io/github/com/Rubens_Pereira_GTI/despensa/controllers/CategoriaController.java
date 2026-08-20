package io.github.com.Rubens_Pereira_GTI.despensa.controllers;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.CategoriaDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.dto.CategoriaResponseDto;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Categoria;
import io.github.com.Rubens_Pereira_GTI.despensa.mapper.CategoriaMapper;
import io.github.com.Rubens_Pereira_GTI.despensa.service.CategoriaService;
import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;
    private final CategoriaMapper categoriaMapper;

    public CategoriaController(CategoriaService categoriaService, CategoriaMapper categoriaMapper) {
        this.categoriaService = categoriaService;
        this.categoriaMapper = categoriaMapper;
    }

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid CategoriaDTO dto) {

        Categoria categoria = categoriaMapper.toEntity(dto);
        categoriaService.salvarCategoria(categoria);

        URI location = ServletUriComponentsBuilder.
                    fromCurrentRequest().
                    path("/{id}").
                    buildAndExpand(categoria.getId()).
                    toUri();

        return ResponseEntity.created(location).build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDto> buscaPorId(@PathVariable Long id){
        Categoria categoria = categoriaService.buscaCategoriaPorId(id);
        CategoriaResponseDto categoriaDTO = categoriaMapper.toResponseDTO(categoria);
        return ResponseEntity.ok(categoriaDTO);
    }

    @GetMapping
    ResponseEntity<Page<CategoriaResponseDto>> buscarTodasPorLocal(                                 
                                    @RequestParam(name = "local_id") Long localId,
                                    @RequestParam(required = false, defaultValue = "0") int page,
                                    @RequestParam(required = false, defaultValue = "10") int size,
                                    @RequestParam(required = false, defaultValue = "id") String sortField,
                                    @RequestParam(required = false, defaultValue = "asc") String sortOrder,
                                    @RequestParam(required = false, defaultValue = "true") boolean ativo,
                                    @RequestParam(required = false, defaultValue = "") String nome
                                ){                                    
        Page<Categoria> categorias = categoriaService.pesquisaPaginada(localId, page, size, sortField, sortOrder, ativo, nome);
        Page<CategoriaResponseDto> dtos = categorias.map(cat -> categoriaMapper.toResponseDTO(cat));        
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{id}")
    ResponseEntity<Object> alterar(@PathVariable Long id,
                                             @RequestBody @Valid CategoriaDTO dto){
                                                
        Categoria categoria = categoriaMapper.toEntity(dto);
        categoriaService.alterarCategoria(categoria, id);        
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{id}")
    ResponseEntity<Object> deletar(@PathVariable Long id){
        categoriaService.deletar(id);
        return ResponseEntity.noContent().build();

    }


}
