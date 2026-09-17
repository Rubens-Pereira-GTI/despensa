package io.github.com.Rubens_Pereira_GTI.despensa.service.strategy;

import io.github.com.Rubens_Pereira_GTI.despensa.entity.TipoMovimentacao;
import io.github.com.Rubens_Pereira_GTI.despensa.exception.OperacaoNaoPermitidaException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MovimentacaoStrategyFactory {

    private final Map<TipoMovimentacao, MovimentacaoStrategy> strategies;

    public MovimentacaoStrategyFactory(List<MovimentacaoStrategy> strategyList) {
        this.strategies = strategyList.stream()
                .collect(Collectors.toMap(MovimentacaoStrategy::getTipo, s -> s));
    }

    public MovimentacaoStrategy getStrategy(TipoMovimentacao tipo) {
        MovimentacaoStrategy strategy = strategies.get(tipo);
        if (strategy == null) {
            throw new OperacaoNaoPermitidaException("Tipo de movimentação não suportado: " + tipo);
        }
        return strategy;
    }
}
