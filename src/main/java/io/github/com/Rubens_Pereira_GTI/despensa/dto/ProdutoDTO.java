package io.github.com.Rubens_Pereira_GTI.despensa.dto;


import io.github.com.Rubens_Pereira_GTI.despensa.entity.Produto;

import java.math.BigDecimal;

public record ProdutoDTO(
        Long id,
        String nome,
        String descricao,
        BigDecimal estoqueMinimo,
        boolean ativo,
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
