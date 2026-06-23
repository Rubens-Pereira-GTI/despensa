package io.github.com.Rubens_Pereira_GTI.despensa.entity;

import jakarta.persistence.*;

@Entity
@Table
public class Estoque {

    //TODO [Reverse Engineering] generate columns from DB
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Produto produto;

}