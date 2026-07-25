package io.github.com.Rubens_Pereira_GTI.despensa.controllers;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.EstoqueDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Estoque;
import io.github.com.Rubens_Pereira_GTI.despensa.service.EstoqueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/estoques")
public class EstoqueController {

    private final EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService){
        this.estoqueService = estoqueService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstoqueDTO> buscarEstoque(@PathVariable Long id){
        Optional<Estoque> estoqueOpt = estoqueService.findEstoque(id);
        if(estoqueOpt.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        EstoqueDTO dto = EstoqueDTO.toDTO(estoqueOpt.get());

        return ResponseEntity.ok(dto);
    }

    //public ResponseEntity<Void>
}
