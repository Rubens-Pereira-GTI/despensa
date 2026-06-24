package io.github.com.Rubens_Pereira_GTI.despensa.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProdutoResponseDTO(
        Long id,
        String nome,
        String descricao,
        BigDecimal estoqueMinimo,
        boolean ativo,
        LocalDateTime dataDeCriacao,
        LocalDateTime dataDeAtualizacao,

        // Dados simplificados dos relacionamentos para o Front-end
        Long categoriaId,

        Long unidadeMedidaId,

        // Dados transientes que fazem sentido retornar na consulta
        Long localId,
        String localizacao
) {

}
