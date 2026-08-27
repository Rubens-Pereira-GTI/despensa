package io.github.com.Rubens_Pereira_GTI.despensa.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.MovimentacaoDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.service.MovimentacaoService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController {

    private final MovimentacaoService movimentacaoService;

    public MovimentacaoController(MovimentacaoService movimentacaoService ){
        this.movimentacaoService = movimentacaoService;
    }

    @PostMapping
    public ResponseEntity<Void> entrada(@RequestBody MovimentacaoDTO dto) {      

        //movimentacaoService.entrada(movimentacao)

        return ResponseEntity.noContent().build();
    }
    





}
