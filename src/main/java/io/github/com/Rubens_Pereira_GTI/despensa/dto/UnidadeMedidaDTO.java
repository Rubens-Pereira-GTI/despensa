package io.github.com.Rubens_Pereira_GTI.despensa.dto;

import jakarta.validation.constraints.NotBlank;

public record UnidadeMedidaDTO(
    
    Long id,    
    String nome,
    @NotBlank
    String sigla
) {
    
}
