package io.github.com.Rubens_Pereira_GTI.despensa.entity;

import jakarta.persistence.*;

@Entity
@Table(schema = "social", name = "local")
public class Local {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
}
