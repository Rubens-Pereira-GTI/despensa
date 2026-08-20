package io.github.com.Rubens_Pereira_GTI.despensa.repository;

import io.github.com.Rubens_Pereira_GTI.despensa.entity.Categoria;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Optional<Produto> findByCategoria(Categoria categoria);

    boolean existsByCategoria(Categoria categoria);

    Optional<Produto> findByNomeIgnoreCase(String nome);

    Optional<Produto> findByNome(String nome);

    boolean existsById(Long id);


}
