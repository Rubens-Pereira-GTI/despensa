package io.github.com.Rubens_Pereira_GTI.despensa.dto;

import io.github.com.Rubens_Pereira_GTI.despensa.entity.Local;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LocalDTO(

        Long id,

        @Size(max = 100)
        @NotBlank(message = "campo obrigatorio")
        String nome,

        @Size(max = 255)
        String descricao,

        @NotNull(message = "campo obrigatorio")
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
