package io.github.com.Rubens_Pereira_GTI.despensa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(schema = "social", name = "estoque")
public class Estoque {

    //TODO [Reverse Engineering] generate columns from DB
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Produto produto;

    //kg, mg, L
    @PositiveOrZero(message = "quantidade minima não pode ser negativa")
    @Column(name = "quantidade", precision = 10, scale = 2)
    private BigDecimal quantidade;

    @Column(name = "quantidade_reservada", precision = 10, scale = 2)
    @PositiveOrZero(message = " quanttidade reservada não pode ser negativa")
    private BigDecimal qtd_reservada;

    @Column(name = "localizacao")
    @Size(max = 100, message = "A localizacao deve ter no maximo 100 caracteres")
    private String localizacao;

    @Column(name = "data_validade")
    private LocalDate dataValidade;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

}