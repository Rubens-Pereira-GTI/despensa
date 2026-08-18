package io.github.com.Rubens_Pereira_GTI.despensa.repository;

import io.github.com.Rubens_Pereira_GTI.despensa.entity.Categoria;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Optional<Categoria> findCategoriaByNome(String nome);

    boolean existsByLocal(Local local);

    @Query("SELECT c.local FROM Categoria c WHERE c.id = :categoriaId")
    Optional<Local> findLocalByCategoria(@Param("categoriaId") Long categoriaId);


}
