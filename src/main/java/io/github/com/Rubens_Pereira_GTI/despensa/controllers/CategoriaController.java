package io.github.com.Rubens_Pereira_GTI.despensa.controllers;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.CategoriaRequestDto;
import io.github.com.Rubens_Pereira_GTI.despensa.dto.CategoriaResponseDto;
import io.github.com.Rubens_Pereira_GTI.despensa.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDto> buscaCategoriaId(@PathVariable Long id){
        CategoriaResponseDto responseDto = categoriaService.buscaCategoriaId(id);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<Page<CategoriaResponseDto>> buscaTodasCategoriasPaginada(
            @PageableDefault(size = 10) Pageable pageable) {
        Page<CategoriaResponseDto> responseDto = categoriaService.buscaTodasCategoriasPaginada(pageable);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping
    public ResponseEntity<CategoriaResponseDto> salvaCategoria(@RequestBody @Valid CategoriaRequestDto requestDto) {
        CategoriaResponseDto responseDto = categoriaService.salvarCategoria(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    //Não consigo checar id no json preciso pegar pela pathvariavle para garantir que venha
    @PutMapping("/{id}")
    ResponseEntity<CategoriaResponseDto> atualizaCategoria(@PathVariable Long id, @RequestBody @Valid CategoriaRequestDto requestDto){
        CategoriaResponseDto responseDto = categoriaService.alterarCategoria(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletaCategoria(@PathVariable Long id){
        categoriaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
