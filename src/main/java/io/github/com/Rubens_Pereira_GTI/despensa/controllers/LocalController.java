package io.github.com.Rubens_Pereira_GTI.despensa.controllers;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.LocalDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Local;
import io.github.com.Rubens_Pereira_GTI.despensa.mapper.LocalMapper;
import io.github.com.Rubens_Pereira_GTI.despensa.service.LocalService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/locais")
public class LocalController {

    private final LocalService localService;
    private final LocalMapper localMapper;

    public LocalController(LocalService localService, LocalMapper localMapper) {
        this.localService = localService;
        this.localMapper = localMapper;
    }

    @PostMapping
    public ResponseEntity<Object> salvar(@Valid @RequestBody LocalDTO localDTO) {

        Local local = localMapper.toEntity(localDTO);

        local = localService.salvar(local);

        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{id}").
                buildAndExpand(local.getId()).
                toUri();

        return ResponseEntity.created(location).build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<LocalDTO> buscarLocalPorId(@PathVariable Long id) {
        Local local = localService.buscarPorId(id);
        LocalDTO localDTO = localMapper.toDTO(local);
        return ResponseEntity.ok(localDTO);
    }


    @GetMapping
    public ResponseEntity<List<LocalDTO>> buscaTodosLocais() {
        List<Local> locais = localService.findAll();

        List<LocalDTO> dtos = locais.stream().map(localMapper::toDTO).toList();

        return ResponseEntity.ok(dtos);
    }

    //TODO fazer um get paginado

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleta(@PathVariable Long id) {
        localService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> alterar(@Valid @RequestBody LocalDTO localDTO, @PathVariable Long id) {
        
        Local local = localMapper.toEntity(localDTO);
        
        local = localService.alterar(id, local);

        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{id}").
                buildAndExpand(local.getId()).
                toUri();

                
        return ResponseEntity.created(location).build();

    }


}
