package io.github.com.Rubens_Pereira_GTI.despensa.repository;

import io.github.com.Rubens_Pereira_GTI.despensa.entity.Categoria;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.Optional;
public interface ProdutoRepository extends JpaRepository<Produto, Long>, JpaSpecificationExecutor<Produto> {

    Optional<Produto> findByCategoria(Categoria categoria);

    boolean existsByCategoria(Categoria categoria);

    Optional<Produto> findByNomeIgnoreCase(String nome);

    Optional<Produto> findByNome(String nome);

    boolean existsById(Long id);

    @Override
    @EntityGraph(attributePaths = {"categoria.local"} ,type = EntityGraphType.LOAD)
    Page<Produto> findAll(Specification<Produto> spec, Pageable pageable);

}
