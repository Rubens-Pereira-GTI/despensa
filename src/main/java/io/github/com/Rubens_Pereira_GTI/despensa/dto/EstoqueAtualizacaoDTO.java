package io.github.com.Rubens_Pereira_GTI.despensa.dto;

import java.time.LocalDate;

public record EstoqueAtualizacaoDTO(
    String localizacao,
    LocalDate dataValidade
) {
    
}
