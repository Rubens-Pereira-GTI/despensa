package io.github.com.Rubens_Pereira_GTI.despensa.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Movimentacao;


import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long>, JpaSpecificationExecutor<Movimentacao> {

    Optional<Movimentacao> findByProdutoId(Long produto);
    
    @Override
    @EntityGraph(attributePaths = {"produto"})
    Page<Movimentacao> findAll(Specification<Movimentacao> spec, Pageable page);

    @EntityGraph(attributePaths = {"produto"}, type = EntityGraph.EntityGraphType.LOAD)
    Page<Movimentacao> findByProduto_Categoria_Local_Id(Long localId, Pageable pageable);
    
}
