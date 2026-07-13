package io.github.com.Rubens_Pereira_GTI.despensa.dto;

import io.github.com.Rubens_Pereira_GTI.despensa.entity.Local;

import java.util.UUID;

public record LocalDTO(
        Long id,
        String nome,
        String descricao,
        Boolean ativo

) {
    public Local toLocal(){
        Local local = new Local();

        local.setNome(this.nome);
        local.setDescricao(this.descricao);
        local.setAtivo(this.ativo);

        return  local;
    }
}
