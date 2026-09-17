package io.github.com.Rubens_Pereira_GTI.despensa.service.strategy;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.MovimentacaoDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Estoque;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.TipoMovimentacao;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class EntradaStrategy implements MovimentacaoStrategy {

    @Override
    public TipoMovimentacao getTipo() {
        return TipoMovimentacao.ENTRADA;
    }

    @Override
    public BigDecimal executar(Estoque estoque, MovimentacaoDTO dto) {
        if (dto.dataValidade() != null) {
            estoque.setDataValidade(dto.dataValidade());
        }
        if (dto.localizacao() != null) {
            estoque.setLocalizacao(dto.localizacao());
        }
        return estoque.registrarEntrada(dto.quantidade());
    }
}
