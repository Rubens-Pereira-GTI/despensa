package io.github.com.Rubens_Pereira_GTI.despensa.repository;

import io.github.com.Rubens_Pereira_GTI.despensa.entity.Categoria;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Optional<Categoria> findByNomeIgnoringCase(String nome);

    boolean existsByLocal(Local local);



}
