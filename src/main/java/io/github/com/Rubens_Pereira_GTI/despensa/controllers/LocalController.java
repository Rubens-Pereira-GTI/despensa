package io.github.com.Rubens_Pereira_GTI.despensa.controllers;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.LocalDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Local;
import io.github.com.Rubens_Pereira_GTI.despensa.service.LocalService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/local")
public class LocalController {

    private LocalService localService;

    public LocalController(LocalService localService){
        this.localService = localService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocalDTO> buscarLocalPorid(@PathVariable Long id){

        Optional<Local> localOpt = localService.buscarPorId(id);

        if(localOpt.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        LocalDTO localDTO = new LocalDTO(
                localOpt.get().getId(),
                localOpt.get().getNome(),
                localOpt.get().getDescricao(),
                localOpt.get().getAtivo()
        );

        return ResponseEntity.ok(localDTO);
    }

    @PostMapping
    public ResponseEntity<Void> salvar(@Valid @RequestBody LocalDTO localDTO){

        Local local = localService.salvarLocal(localDTO);

        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{id}").
                buildAndExpand(local.getId()).
                toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleta(@PathVariable Long id){

        Optional<Local> localOpt = localService.buscarPorId(id);
        if(localOpt.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        localService.deletar(localOpt.get().getId());

        return ResponseEntity.noContent().build();

    }

    //TODO Verificar se é necessário retornar o ID se não, fazer um DTOresponse
    @PutMapping("/{id}")
    public ResponseEntity<Local> alterar(@Valid @RequestBody LocalDTO localDTO){

        Optional<Local> localOpt = localService.buscarPorId(localDTO.id());
        if(localOpt.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Local local = localService.alterarLocal(localDTO);
        return ResponseEntity.ok(local);
    }

    @GetMapping
    public ResponseEntity<List<LocalDTO>> buscaTodosLocais(){
        List<Local> locais = localService.findAll();

        List<LocalDTO> dtos = locais.stream().map(loc -> {
                return new LocalDTO(
                        loc.getId(),
                        loc.getNome(),
                        loc.getDescricao(),
                        loc.getAtivo()
                );
                }).toList();

        return ResponseEntity.ok(dtos);
    }

    //TODO verificar se tem que devolver uma pagina com dtos
    @GetMapping("/paginado")
    public ResponseEntity<Page<Local>> buscarLocaisPaginado(
            @PageableDefault(size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable ){

        Page<Local> pgDtos = localService.buscaPaginada(pageable);

        return ResponseEntity.ok(localService.buscaPaginada(pageable));
    }







}
