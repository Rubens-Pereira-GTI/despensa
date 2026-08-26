package io.github.com.Rubens_Pereira_GTI.despensa.repository;

import io.github.com.Rubens_Pereira_GTI.despensa.entity.Categoria;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Local;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Produto;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Optional<Categoria> findByNomeIgnoringCase(String nome);

    boolean existsByLocal(Local local);
    @Override
    @EntityGraph(attributePaths = "local")
    <S extends Categoria> Page<S> findAll(Example<S> example, Pageable pageable);

    Optional<Categoria> findByProdutosContaining(Produto produto);

}

