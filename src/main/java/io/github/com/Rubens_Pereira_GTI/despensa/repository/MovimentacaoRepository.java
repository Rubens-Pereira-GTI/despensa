package io.github.com.Rubens_Pereira_GTI.despensa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.MovimentacaoHistoricoResponse;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Movimentacao;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

    @Query("""
        SELECT new io.github.com.Rubens_Pereira_GTI.despensa.dto.MovimentacaoHistoricoResponse(
            m.id,
            p.nome,
            u.sigla,
            m.quantidade,
            m.tipoMovimentacao,
            m.motivo
        )
        FROM Movimentacao m
        JOIN m.produto p
        JOIN p.unidadeMedida u
        JOIN p.categoria c 
        JOIN c.local l
        WHERE (:localId IS NULL OR l.id = :localId)
    """)
    Page<MovimentacaoHistoricoResponse> findAllResponse(@Param("localId") Long localId, Pageable pageable);
}
