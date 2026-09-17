package io.github.com.Rubens_Pereira_GTI.despensa.service;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.MovimentacaoDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Estoque;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Movimentacao;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Produto;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.TipoMovimentacao;
import io.github.com.Rubens_Pereira_GTI.despensa.exception.OperacaoNaoPermitidaException;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.EstoqueRepository;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.MovimentacaoRepository;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.ProdutoRepository;
import io.github.com.Rubens_Pereira_GTI.despensa.service.strategy.MovimentacaoStrategy;
import io.github.com.Rubens_Pereira_GTI.despensa.service.strategy.MovimentacaoStrategyFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovimentacaoService {

    private final EstoqueRepository estoqueRepository;
    private final MovimentacaoRepository movimentacaoRepository;
    private final ProdutoRepository produtoRepository;
    private final MovimentacaoStrategyFactory strategyFactory;

    public MovimentacaoService(EstoqueRepository estoqueRepository,
                               MovimentacaoRepository movimentacaoRepository,
                               ProdutoRepository produtoRepository,
                               MovimentacaoStrategyFactory strategyFactory) {
        this.estoqueRepository = estoqueRepository;
        this.movimentacaoRepository = movimentacaoRepository;
        this.produtoRepository = produtoRepository;
        this.strategyFactory = strategyFactory;
    }

    @Transactional
    public void movimentar(MovimentacaoDTO dto) {
        Produto produto = produtoRepository.findById(dto.produtoId())
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        Estoque estoque = estoqueRepository.findByProduto_Id(dto.produtoId())
                .orElseGet(() -> {
                    if (dto.tipoMovimentacao() != TipoMovimentacao.ENTRADA) {
                        throw new OperacaoNaoPermitidaException("Não é possível realizar saída de produto sem estoque cadastrado.");
                    }
                    Estoque novo = new Estoque();
                    novo.setProduto(produto);
                    novo.setQuantidade(BigDecimal.ZERO);
                    novo.setQtdReservada(BigDecimal.ZERO);
                    return novo;
                });

        BigDecimal saldoAnterior = estoque.getQuantidade();

        //BigDecimal saldoNovo = strategyFactory.getStrategy(dto.tipoMovimentacao()).executar(estoque, dto);
        MovimentacaoStrategy strategy = strategyFactory.getStrategy(dto.tipoMovimentacao());
        BigDecimal saldoNovo = strategy.executar(estoque, dto);

        estoqueRepository.save(estoque);

        Movimentacao movimentacao = Movimentacao.criar(
                produto,
                dto.tipoMovimentacao(),
                dto.quantidade(),
                saldoAnterior,
                saldoNovo,
                dto.motivo()
        );

        movimentacaoRepository.save(movimentacao);
    }

    @Transactional(readOnly = true)
    public Page<Movimentacao> buscarMovimetacoes(Integer pageNumber, Integer pageSize, Long localId, LocalDateTime dataMovimentacao, TipoMovimentacao tipoMovimentacao) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Specification<Movimentacao> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            
             // 1. Filtro por Local (Movimentacao -> Produto -> Categoria -> Local -> id)
            if (localId != null) {
                predicates.add(cb.equal(
                    root.join("produto")
                        .join("categoria")
                        .join("local")
                        .get("id"),
                    localId
                ));
            }

            // 2. Filtro por Tipo de Movimentação (ENTRADA, SAIDA, etc.)
            if (tipoMovimentacao != null) {
                predicates.add(cb.equal(root.get("tipoMovimentacao"), tipoMovimentacao));
            }
            // 3. Filtro por Data de Movimentação
            if (dataMovimentacao != null) {
                // DICA: Como a data gravada tem hora/minuto/segundo, 
                // comparar o DIA INTEIRO (00:00:00 até 23:59:59) evita não achar registros por diferença de segundos:
                LocalDateTime inicioDoDia = dataMovimentacao.toLocalDate().atStartOfDay();
                LocalDateTime fimDoDia = dataMovimentacao.toLocalDate().atTime(LocalTime.MAX);
                
                predicates.add(cb.between(root.get("dataMovimentacao"), inicioDoDia, fimDoDia));
                
                /* Se você quiser comparar a data e hora EXATA ao segundo, use:
                predicates.add(cb.equal(root.get("dataMovimentacao"), dataMovimentacao));
                */
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return movimentacaoRepository.findAll(spec, pageable);
        
    }
}
