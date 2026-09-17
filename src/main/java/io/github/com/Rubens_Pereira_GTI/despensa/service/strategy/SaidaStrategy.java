package io.github.com.Rubens_Pereira_GTI.despensa.service.strategy;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.MovimentacaoDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Estoque;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.TipoMovimentacao;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SaidaStrategy implements MovimentacaoStrategy {

    @Override
    public TipoMovimentacao getTipo() {
        return TipoMovimentacao.SAIDA;
    }

    @Override
    public BigDecimal executar(Estoque estoque, MovimentacaoDTO dto) {
        estoque.registrarSaida(dto.quantidade());
        return estoque.getQuantidade();
    }
}
