package io.github.com.Rubens_Pereira_GTI.despensa.repository;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.LocalResumoDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Categoria;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Local;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LocalRepository extends JpaRepository<Local, Long> {

    Optional<Local> findByNomeIgnoringCase(String nome);

    List<Local> findLocalByCategorias(List<Categoria> categorias);


    Optional<Local> findByCategoriasContaining(Categoria categoria);

    List<LocalResumoDTO> findAllResumoByAtivoTrue();

}

