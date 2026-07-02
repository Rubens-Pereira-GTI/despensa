package io.github.com.Rubens_Pereira_GTI.despensa.controllers;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.LocalResponseDto;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Local;
import io.github.com.Rubens_Pereira_GTI.despensa.service.LocalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/local")
public class LocalController {

    private LocalService localService;

    public LocalController(LocalService localService){
        this.localService = localService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Local> findLocalById(@PathVariable Long id){
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



}
