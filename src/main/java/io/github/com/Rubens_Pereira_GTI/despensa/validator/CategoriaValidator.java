package io.github.com.Rubens_Pereira_GTI.despensa.validator;

import io.github.com.Rubens_Pereira_GTI.despensa.entity.Categoria;
import io.github.com.Rubens_Pereira_GTI.despensa.exception.RegistroDuplicadoException;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.CategoriaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CategoriaValidator {

    private final CategoriaRepository categoriaRepository;

    public CategoriaValidator(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }

    public void validar(Categoria categoria){
        if(existeCategoriaCadastrada(categoria)){
            throw new RegistroDuplicadoException("Essa categoria já existe");
        }
    }

    public boolean existeCategoriaCadastrada(Categoria categoria){

        Optional<Categoria> categoriaOpt = categoriaRepository.findCategoriaByNome(categoria.getNome());

        if(categoria.getId() == null){
            return categoriaOpt.isPresent();
        }

        return categoriaOpt.isPresent() && !categoriaOpt.get().getId().equals(categoria.getId());
    }
}
