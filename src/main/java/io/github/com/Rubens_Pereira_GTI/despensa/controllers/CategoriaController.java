package io.github.com.Rubens_Pereira_GTI.despensa.controllers;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.CategoriaDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.dto.CategoriaResponseDto;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Categoria;
import io.github.com.Rubens_Pereira_GTI.despensa.exception.RegistroDuplicadoException;
import io.github.com.Rubens_Pereira_GTI.despensa.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> buscaCategoriaPorId(@PathVariable Long id){

        Optional<Categoria> categoriaOpt = categoriaService.buscaCategoriaPorId(id);

        if(categoriaOpt.isEmpty()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Categoria categoria = categoriaOpt.get();

        CategoriaDTO categoriaDTO = new CategoriaDTO(
                categoria.getId(),
                categoria.getNome(),
                categoria.getDescricao(),
                categoria.getLocalId()
        );

        return ResponseEntity.ok(categoriaDTO);
    }

    @GetMapping
    public ResponseEntity<Page<CategoriaResponseDto>> buscaTodasCategoriasPaginada(
            @PageableDefault(size = 10) Pageable pageable) {
        Page<CategoriaResponseDto> responseDto = categoriaService.buscaTodasCategoriasPaginada(pageable);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping
    public ResponseEntity<Object> salvaCategoria(@RequestBody @Valid CategoriaDTO dto) {

        Categoria categoria = dto.toCategoria();
        //TODO acho que tenho que verifica se ela existe pois se existe uma igual não deve ser salva
        try {
            categoriaService.salvarCategoria(categoria);

            return ResponseEntity.status(HttpStatus.CREATED.value()).build();

        } catch (RegistroDuplicadoException ex){

            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage()); //TODO no Body fazer um objeto de erro para retornar ele no body

        }
    }

    //Não consigo checar id no json preciso pegar pela pathvariavle para garantir que venha
    @PutMapping("/{id}")
    ResponseEntity<CategoriaResponseDto> atualizaCategoria(@PathVariable Long id, @RequestBody @Valid CategoriaDTO dto){

        Optional<Categoria> categoriaOpt = categoriaService.buscaCategoriaPorId(id);
        try {
            if (categoriaOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); //TODO pesquisar a função do .build()
            }

            Categoria categoria = dto.toCategoria();
            categoriaService.alterarCategoria(id, categoria);
            return ResponseEntity.noContent().build();

        }catch (RegistroDuplicadoException ex){
            //TODO fazer um objeto err para armazenas os valores do erro
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletaCategoria(@PathVariable Long id){
        categoriaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
