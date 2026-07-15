package io.github.com.Rubens_Pereira_GTI.despensa.validator;

import io.github.com.Rubens_Pereira_GTI.despensa.entity.Local;
import io.github.com.Rubens_Pereira_GTI.despensa.exception.RegistroDuplicadoException;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.LocalRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LocalValidator {

    private final LocalRepository localRepository;

    public LocalValidator(LocalRepository localRepository){
        this.localRepository = localRepository;
    }


    public void validar(Local local){

        if(existeLocalCadastrado(local)){
            throw new RegistroDuplicadoException("Esse local já existe");
        }
    }

    private boolean existeLocalCadastrado(Local local){
        Optional<Local> localOpt = localRepository.findByNome(local.getNome());

        //Locais novos entram nesse teste
        if(local.getId() == null){
            return localOpt.isPresent();
        }
        //Locais atualizados entram nessa codição, se tiver presente e não tiver o mesmo id, então está duplicado (true)
        return localOpt.isPresent() && !local.getId().equals(localOpt.get().getId());

    }
}
