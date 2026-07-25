package io.github.com.Rubens_Pereira_GTI.despensa.dto;


import io.github.com.Rubens_Pereira_GTI.despensa.entity.Produto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProdutoDTO(
        Long id,
        @NotBlank String nome,
        String descricao,
        @NotNull BigDecimal estoqueMinimo,
        @NotNull Boolean ativo,
        Long categoriaId,
        Long localId,
        String localizacao

) {
    public Produto toProduto(){
        Produto produto = new Produto();
        produto.setId(this.id);
        produto.setNome(this.nome);
        produto.setDescricao(this.descricao);
        produto.setAtivo(this.ativo);
        produto.setCategoriaId(this.categoriaId);
        produto.setLocalId(this.localId);
        produto.setLocalizacao(this.localizacao);
        return  produto;
    }
}
