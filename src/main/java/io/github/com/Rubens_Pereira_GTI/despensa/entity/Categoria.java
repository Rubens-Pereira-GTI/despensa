package io.github.com.Rubens_Pereira_GTI.despensa.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Produto> listaProdutos;


}
