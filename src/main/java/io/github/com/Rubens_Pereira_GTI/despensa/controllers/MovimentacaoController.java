package io.github.com.Rubens_Pereira_GTI.despensa.controllers;

import io.github.com.Rubens_Pereira_GTI.despensa.mapper.MovimentacaoMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.MovimentacaoDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.dto.MovimentacaoHistoricoResponse;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Movimentacao;
import io.github.com.Rubens_Pereira_GTI.despensa.service.MovimentacaoService;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController {

    private final MovimentacaoMapper movimentacaoMapper;
    private final MovimentacaoService movimentacaoService;

    public MovimentacaoController(MovimentacaoService movimentacaoService, MovimentacaoMapper movimentacaoMapper ){
        this.movimentacaoService = movimentacaoService;
        this.movimentacaoMapper = movimentacaoMapper;
    }

    @PostMapping
    public ResponseEntity<Void> movimentar(@RequestBody MovimentacaoDTO dto){
        Movimentacao movimentacao = movimentacaoMapper.toEntity(dto);
        movimentacaoService.movimentar(movimentacao);
        return ResponseEntity.accepted().build();
    }

    //TODO Testar
    //precisa retornar uma lista de produtos a sigla da unidade de medida 
    //tem que ser uma lista de produtos associada aquele local
    @GetMapping
    public ResponseEntity<Page<MovimentacaoHistoricoResponse>> buscarHistorico(
        @RequestParam(defaultValue = "0", required = false) Integer page,
        @RequestParam(defaultValue = "10", required = false) Integer size,
        @RequestParam(defaultValue = "id", required = false) String orderBy,
        @RequestParam(defaultValue = "desc", required = false) String direction,
        @RequestParam(required = false) Long localId) {

        Page<MovimentacaoHistoricoResponse> buscaFiltrada = movimentacaoService.buscaFiltrada(page, size, orderBy, direction, localId);

        return ResponseEntity.ok(buscaFiltrada);
    }
    


    





}
