package io.github.com.Rubens_Pereira_GTI.despensa.controllers;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.ErroResponse;
import io.github.com.Rubens_Pereira_GTI.despensa.dto.LocalDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Local;
import io.github.com.Rubens_Pereira_GTI.despensa.exception.OperacaoNaoPermitidaException;
import io.github.com.Rubens_Pereira_GTI.despensa.exception.RegistroDuplicadoException;
import io.github.com.Rubens_Pereira_GTI.despensa.service.LocalService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/locais")
public class LocalController {

    private static final Logger log = LoggerFactory.getLogger(LocalController.class);
    private final LocalService localService;

    public LocalController(LocalService localService){
        this.localService = localService;
    }

    @PostMapping
    public ResponseEntity<Object> salvar(@Valid @RequestBody LocalDTO localDTO){

        Local local = localDTO.toLocal();

        try {
            localService.salvarLocal(local);

            URI location = ServletUriComponentsBuilder.
                    fromCurrentRequest().
                    path("/{id}").
                    buildAndExpand(local.getId()).
                    toUri();

            return ResponseEntity.created(location).build();
        }catch (RegistroDuplicadoException ex){
            ErroResponse erroResponse = ErroResponse.conflito(ex.getMessage());
            return ResponseEntity.status(erroResponse.status()).body(erroResponse);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocalDTO> buscarLocalPorId(@PathVariable Long id){

        Optional<Local> localOpt = localService.buscarPorId(id);

        if(localOpt.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        LocalDTO localDTO = LocalDTO.fromLocal(localOpt.get());

        return ResponseEntity.ok(localDTO);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleta(@PathVariable Long id){

        Optional<Local> localOpt = localService.buscarPorId(id);
        if(localOpt.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        try{
            localService.deletar(localOpt.get());
            return ResponseEntity.noContent().build();

        }catch (OperacaoNaoPermitidaException ex){
            ErroResponse erroResponse = ErroResponse.conflito(ex.getMessage());
            return ResponseEntity.status(erroResponse.status()).body(erroResponse);
        }


    }
    //TODO Verificar se é necessário retornar o ID se não, fazer um DTOresponse

    @PutMapping("/{id}")
    public ResponseEntity<Object> alterar(@Valid @RequestBody LocalDTO localDTO, @PathVariable Long id ){

        try {
            Optional<Local> localOpt = localService.buscarPorId(id);
            if(localOpt.isEmpty()){
                return ResponseEntity.notFound().build();
            }

            //atualiza o local
            Local local = localOpt.get();
            local.setNome(localDTO.nome());
            local.setDescricao(localDTO.descricao());
            local.setAtivo(localDTO.ativo());

            localService.alterarLocal(local);
            return ResponseEntity.noContent().build();

        } catch (RegistroDuplicadoException ex) {
            ErroResponse erroResponse = ErroResponse.conflito(ex.getMessage());
            return ResponseEntity.status(erroResponse.status()).body(erroResponse);
        }

    }


}
