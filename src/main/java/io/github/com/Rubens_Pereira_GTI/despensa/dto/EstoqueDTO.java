package io.github.com.Rubens_Pereira_GTI.despensa.dto;

import io.github.com.Rubens_Pereira_GTI.despensa.entity.Estoque;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record EstoqueDTO(
        Long id,
        Long produtoId,
        String produtoNome,
        BigDecimal quantidade,
        BigDecimal qtdReservada,
        String localizacao,
        LocalDate dataValidade,
        LocalDateTime dataCriacao,
        LocalDateTime dataAtualizacao
) {

    public Estoque toEstoque(){
        Estoque estoque = new Estoque();
        estoque.setProdutoId(this.id);
        estoque.setQuantidade(this.quantidade);
        estoque.setQtdReservada(this.qtdReservada);
        estoque.setLocalizacao(this.localizacao);
        estoque.setDataValidade(this.dataValidade);
        estoque.setDataCriacao(this.dataCriacao);
        estoque.setDataAtualizacao(this.dataAtualizacao);
        return estoque;
    }

    public static EstoqueDTO toDTO(Estoque estoque){
        return new EstoqueDTO(
                estoque.getId(),
                estoque.getProdutoId(),
                estoque.getNomeProduto(),
                estoque.getQuantidade(),
                estoque.getQtdReservada(),
                estoque.getLocalizacao(),
                estoque.getDataValidade(),
                estoque.getDataCriacao(),
                estoque.getDataAtualizacao()
        );
    }
}
