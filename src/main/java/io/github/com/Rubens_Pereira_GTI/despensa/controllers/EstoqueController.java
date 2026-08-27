package io.github.com.Rubens_Pereira_GTI.despensa.controllers;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.EstoqueDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.dto.EstoqueResponseDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Estoque;
import io.github.com.Rubens_Pereira_GTI.despensa.mapper.EstoqueMapper;
import io.github.com.Rubens_Pereira_GTI.despensa.service.EstoqueService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/estoques")
public class EstoqueController {

    private final EstoqueService estoqueService;
    private final EstoqueMapper estoqueMapper;

    public EstoqueController(EstoqueService estoqueService, EstoqueMapper estoqueMapper){
        this.estoqueService = estoqueService;
        this.estoqueMapper = estoqueMapper;
    }

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody EstoqueDTO dto) {        
        Estoque estoque = estoqueMapper.toEstoque(dto);
        estoqueService.salvar(estoque);
        return ResponseEntity.accepted().build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<EstoqueResponseDTO> buscarPorId(@PathVariable Long id){
        Estoque estoque = estoqueService.buscarPorId(id);
        EstoqueResponseDTO dto = estoqueMapper.toResponseDTO(estoque);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<EstoqueResponseDTO>> listarEstoque(
        @RequestParam (required = false, defaultValue = "0") Integer page, 
        @RequestParam (required = false, defaultValue = "10") Integer size){
            Page<Estoque> estoques = estoqueService.buscaPaginada(page, size);
            Page<EstoqueResponseDTO> dto = estoques.map(estoqueMapper::toResponseDTO);
            return ResponseEntity.ok(dto);
        }


    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(
                                        @PathVariable Long id, 
                                        @RequestBody EstoqueDTO dto) {
        
        estoqueService.atualizar(dto, id);        
        return ResponseEntity.accepted().build();
    }


        

        
        

}
