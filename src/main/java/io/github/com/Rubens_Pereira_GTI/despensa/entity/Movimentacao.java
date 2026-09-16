package io.github.com.Rubens_Pereira_GTI.despensa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimentacao")
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "campo produto é obrigatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Transient
    private Long produtoId;

    //TODO no Banco tipo_movimentação não tem constraints, fazer isso depois.
    @NotNull(message = "campo tipo de movimentacao é obrigatorio")
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimentacao", nullable = false)
    private TipoMovimentacao tipoMovimentacao;

    @NotNull(message = "campo quantidade é obrigatorio")
    @Positive(message = "campo quantidade deve ser positivo")
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal quantidade;

    @NotNull(message = "campo quantidade anterior é obrigatorio")
    @PositiveOrZero(message = "campo quantidade anterior deve ser positivo")
    @Column(name = "quantidade_anterior", precision = 10, scale = 2, nullable = false)
    private BigDecimal qtdAnterior;

    @NotNull(message = "campo quantidade nova é obrigatorio")
    @PositiveOrZero(message = "campo quantidade nova deve ser positivo")
    @Column(name = "quantidade_nova", precision = 10, scale = 2, nullable = false)
    private BigDecimal qtdNova;

    @Size(max = 255)
    private String motivo;

    @NotNull(message = "campo data de criacao obrigatório")
    @Column(name = "data_criacao", updatable = false, nullable = false)
    private LocalDateTime dataCriacao;

    @NotNull(message = "campo data de movimentacao obrigatório")
    @Column(name = "data_movimentacao", nullable = false)
    private LocalDateTime dataMovimentacao;

    public static Movimentacao criar(Produto produto, 
                                    TipoMovimentacao tipo, 
                                    BigDecimal qtdMovimentada, 
                                    BigDecimal saldoAnterior, 
                                    BigDecimal saldoNovo, 
                                    String motivo) {

        Movimentacao mov = new Movimentacao();
        mov.setProduto(produto);
        mov.setTipoMovimentacao(tipo);
        mov.setQuantidade(qtdMovimentada);
        mov.setQtdAnterior(saldoAnterior);
        mov.setQtdNova(saldoNovo);
        mov.setMotivo(motivo);
    return mov;
}


    @PrePersist
    public void onCreate(){
        dataCriacao = LocalDateTime.now();
        dataMovimentacao = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate(){
        dataMovimentacao = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public TipoMovimentacao getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getQtdAnterior() {
        return qtdAnterior;
    }

    public void setQtdAnterior(BigDecimal qtdAnterior) {
        this.qtdAnterior = qtdAnterior;
    }

    public BigDecimal getQtdNova() {
        return qtdNova;
    }

    public void setQtdNova(BigDecimal qtdNova) {
        this.qtdNova = qtdNova;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(LocalDateTime dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    
}
