package io.github.com.Rubens_Pereira_GTI.despensa.controllers;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.LocalRequestDto;
import io.github.com.Rubens_Pereira_GTI.despensa.dto.LocalResponseDto;
import io.github.com.Rubens_Pereira_GTI.despensa.service.LocalService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/local")
public class LocalController {

    private LocalService localService;

    public LocalController(LocalService localService){
        this.localService = localService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocalResponseDto> findLocalById(@PathVariable Long id){
        return ResponseEntity.ok(localService.findById(id));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<LocalResponseDto>> buscaTodosLocais(){
        List<LocalResponseDto> locais = localService.findAll();
        return ResponseEntity.ok(locais);
    }

    @GetMapping("/paginado")
    public ResponseEntity<Page> buscarLocaisPaginado(
            @PageableDefault(size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable pageablePage ){
        return ResponseEntity.ok(localService.buscaPaginada(pageablePage));
    }

    @PostMapping
    public ResponseEntity<LocalResponseDto> salvar(@Valid @RequestBody LocalRequestDto requestDto){
        LocalResponseDto responseDto = localService.salvarLocal(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocalResponseDto> alterar(@Valid @RequestBody LocalRequestDto requestDto, @PathVariable Long id){
        LocalResponseDto responseDto = localService.alterarLocal(requestDto, id);
        return ResponseEntity.ok(responseDto);
    }



}
