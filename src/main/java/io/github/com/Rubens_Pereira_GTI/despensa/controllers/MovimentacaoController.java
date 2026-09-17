package io.github.com.Rubens_Pereira_GTI.despensa.controllers;

import io.github.com.Rubens_Pereira_GTI.despensa.mapper.MovimentacaoMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.com.Rubens_Pereira_GTI.despensa.dto.MovimentacaoDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.dto.MovimentacaoResponseDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Movimentacao;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.TipoMovimentacao;
import io.github.com.Rubens_Pereira_GTI.despensa.service.MovimentacaoService;
import java.time.LocalDateTime;
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
        movimentacaoService.movimentar(dto);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<Page<MovimentacaoResponseDTO>> buscarMovimetacoes(@RequestParam Integer pageNumber, 
                                                    @RequestParam Integer pageSize,
                                                    @RequestParam Long localId,
                                                    @RequestParam LocalDateTime dataMovimentacao,
                                                    @RequestParam TipoMovimentacao tipoMovimentacao                                                
                                                ) {

        Page<Movimentacao> page = movimentacaoService.buscarMovimetacoes(pageNumber, pageSize, localId, dataMovimentacao, tipoMovimentacao);
        Page<MovimentacaoResponseDTO> map = page.map(mov -> movimentacaoMapper.tResponseDTO(mov));

        return ResponseEntity.ok(map);
    }
    
    


    





}
