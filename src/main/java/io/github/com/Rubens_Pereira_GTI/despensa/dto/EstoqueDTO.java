package io.github.com.Rubens_Pereira_GTI.despensa.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record EstoqueDTO(
        Long id,

        @NotNull(message = "Produto é obrigatório")
        Long produtoId,

        @NotNull(message = "Quantidade é obrigatória")
        @Positive(message = "Quantidade deve ser maior ou igual a 0")
        BigDecimal quantidade,

        @PositiveOrZero(message = "Quantidade reservada deve ser maior ou igual a 0")
        BigDecimal qtdReservada,

        @Size(max = 100, message = "Localização deve ter no máximo 100 caracteres")
        String localizacao,

        @Future(message = "Data de validade deve ser maior que a data atual")
        LocalDate dataValidade
) {

   
}
