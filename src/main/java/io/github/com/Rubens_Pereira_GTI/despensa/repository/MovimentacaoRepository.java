package io.github.com.Rubens_Pereira_GTI.despensa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Movimentacao;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Produto;


public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

    Optional<Movimentacao> findByProdutoId(Long produto);
}
