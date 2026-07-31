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
    public Local buscarPorId(Long id){
        Optional<Local> localOpt = localRepository.findById(id);
        if(localOpt.isEmpty()){
            throw new EntityNotFoundException("O local não existe");
        }
        return localOpt.get();
    }

    @Transactional(readOnly = true)
    public List<Local> findAll(){
        List<Local> local = localRepository.findAll();
        return local;
    }

    public Local salvar(Local local){
        localValidator.validar(local);
        return localRepository.save(local);
    }

    @Transactional
    public Local alterar(Long id, LocalDTO dto){
        Optional<Local> localOpt = localRepository.findById(id);
        if(localOpt.isEmpty()){
            throw new EntityNotFoundException("Local não encontrado");
        }

        localValidator.validar(localOpt.get());

        Local local = localOpt.get();
        local.setNome(dto.nome());
        local.setDescricao(dto.descricao());
        local.setAtivo(dto.ativo());
        return localRepository.save(local);
    }

    //TODO FIX criar um softdelet
    @Transactional
    public void deletar(Long id) {
        Local local = buscarPorId(id);

        if(categoriaRepository.existsByLocal(local)){
            throw new OperacaoNaoPermitidaException("Não é permitido excluir o local com Categorias vinculadas");
        }
        local.setAtivo(false);
    }


}
