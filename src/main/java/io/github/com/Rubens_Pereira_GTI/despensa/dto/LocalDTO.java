package io.github.com.Rubens_Pereira_GTI.despensa.dto;

import io.github.com.Rubens_Pereira_GTI.despensa.entity.Local;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LocalDTO(
        Long id,
        @NotBlank
        String nome,

        String descricao,
        @NotNull
        Boolean ativo

) {
    public Local toLocal(){
        Local local = new Local();
        local.setId(this.id);
        local.setNome(this.nome);
        local.setDescricao(this.descricao);
        local.setAtivo(this.ativo);
        return  local;
    }

    public static LocalDTO fromLocal(Local local){
        LocalDTO dto = new LocalDTO(
                local.getId(),
                local.getNome(),
                local.getDescricao(),
                local.getAtivo()
        );
        return dto;
    }
}
