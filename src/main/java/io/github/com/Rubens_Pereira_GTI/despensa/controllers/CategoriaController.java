package io.github.com.Rubens_Pereira_GTI.despensa.controllers;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.CategoriaDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.dto.CategoriaResponseDto;
import io.github.com.Rubens_Pereira_GTI.despensa.dto.ErroResponse;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Categoria;
import io.github.com.Rubens_Pereira_GTI.despensa.exception.OperacaoNaoPermitidaException;
import io.github.com.Rubens_Pereira_GTI.despensa.mapper.CategoriaMapper;
import io.github.com.Rubens_Pereira_GTI.despensa.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Object> salvaCategoria(@RequestBody @Valid CategoriaDTO dto) {

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
    public ResponseEntity<CategoriaResponseDto> buscaCategoriaPorId(@PathVariable Long id){

        Optional<Categoria> categoriaOpt = categoriaService.buscaCategoria(id);
        if(categoriaOpt.isEmpty()){
            return  ResponseEntity.notFound().build();
        }

        Categoria categoria = categoriaOpt.get();

        CategoriaResponseDto categoriaDTO = categoriaMapper.toResponseDTO(categoria);

        return ResponseEntity.ok(categoriaDTO);
    }

    @GetMapping
    ResponseEntity<List<CategoriaResponseDto>> buscarTodas(){

        List<Categoria> categorias = categoriaService.buscarTodas();

        List<CategoriaResponseDto> dtos = categorias.stream().map(cat -> categoriaMapper.toResponseDTO(cat)).toList();

        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{id}")
    ResponseEntity<Object> atualizaCategoria(@PathVariable Long id,
                                             @RequestBody @Valid CategoriaDTO dto){

        Categoria categoria = categoriaMapper.toEntity(dto);
        categoriaService.atualizarCategoria(categoria, id);
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
