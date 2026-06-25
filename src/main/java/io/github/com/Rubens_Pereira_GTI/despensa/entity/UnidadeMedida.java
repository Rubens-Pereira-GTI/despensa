package io.github.com.Rubens_Pereira_GTI.despensa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(schema = "social", name = "unidade_medida")
public class UnidadeMedida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "Nome deve é obrigatorio")
    @Size(max = 50, message = "O campo deve ter no máximo 50 letras")
    @Column(nullable = false, unique = true)
    private String nome;

    @NotBlank(message = "Sigla obrigatoria")
    @Size(max = 50, message = "Sigla obrigatoria")
    @Column(nullable = false, unique = true)
    private String sigla;

    @Size(max = 255, message = "A descrição deve conter no máximo 255 caracteres")
    private String descricao;

    @Column(nullable = false)
    private boolean ativo = true;

    @Column(nullable = false, unique = true, updatable = false)
    private LocalDateTime dataCriacao;

    @Column(nullable = false, unique = true, updatable = false)
    private LocalDateTime dataAtualizacao;

    //@JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    //@JsonIgnoreProperties("unidadeMedida")
    private List<Produto> listaProdutos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public List<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(List<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }
}