package io.github.com.Rubens_Pereira_GTI.despensa.service;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.LocalDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Categoria;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Local;
import io.github.com.Rubens_Pereira_GTI.despensa.exception.OperacaoNaoPermitidaException;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.CategoriaRepository;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.LocalRepository;
import io.github.com.Rubens_Pereira_GTI.despensa.validator.LocalValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LocalService {

    private final LocalRepository localRepository;
    private final LocalValidator localValidator;
    public final CategoriaRepository categoriaRepository;

    public LocalService(LocalRepository localRepository,
                        LocalValidator localValidator,
                        CategoriaRepository categoriaRepository){
        this.localRepository = localRepository;
        this.localValidator = localValidator;
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Local> buscarPorId(Long id){
        return localRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Local> findAll(){
        List<Local> local = localRepository.findAll();
        return local;
    }

    public Local salvarLocal(Local local){
        localValidator.validar(local);
        return localRepository.save(local);
    }

    @Transactional
    public Local alterarLocal(Local local){
        localValidator.validar(local);
        return localRepository.save(local);
    }

    @Transactional
    public void deletar(Local local) {
        if(categoriaRepository.existsByLocal(local)){
            throw new OperacaoNaoPermitidaException("Não é permitido excluir o local com Categorias vinculadas");
        }
        localRepository.deleteById(local.getId());
    }


}
