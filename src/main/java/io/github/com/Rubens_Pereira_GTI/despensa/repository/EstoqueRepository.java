package io.github.com.Rubens_Pereira_GTI.despensa.repository;

import io.github.com.Rubens_Pereira_GTI.despensa.entity.Estoque;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepository extends JpaRepository<Estoque, Long>{

    @EntityGraph(attributePaths = { "produto" })
    @Override
    Page<Estoque> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"produto"})
    Optional<Estoque> findById( Long id);

    @EntityGraph(attributePaths = {"produto"}, type = EntityGraph.EntityGraphType.LOAD)
    Page<Estoque> findByProduto_Categoria_Local_Id(Long localId, Pageable pageable);

    @EntityGraph(attributePaths = {"produto"}, type = EntityGraph.EntityGraphType.LOAD)
    Optional<Estoque> findByProduto_Id(Long id);
}
