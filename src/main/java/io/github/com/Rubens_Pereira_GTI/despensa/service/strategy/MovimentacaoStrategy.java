package io.github.com.Rubens_Pereira_GTI.despensa.service.strategy;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.MovimentacaoDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Estoque;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.TipoMovimentacao;

import java.math.BigDecimal;

public interface MovimentacaoStrategy {

    TipoMovimentacao getTipo();

    BigDecimal executar(Estoque estoque, MovimentacaoDTO dto);
}
