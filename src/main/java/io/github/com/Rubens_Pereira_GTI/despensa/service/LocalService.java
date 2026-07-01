package io.github.com.Rubens_Pereira_GTI.despensa.service;

import io.github.com.Rubens_Pereira_GTI.despensa.entity.Local;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.LocalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocalService {

    private final LocalRepository localRepository;

    public LocalService(LocalRepository localRepository){
        this.localRepository = localRepository;
    }

    public Local findById(Long id){
        Optional<Local> localOpt = localRepository.findById(id);

        if(localOpt.isEmpty()) throw new RuntimeException();

        return localOpt.get() ;
    }

    public List<Local> findAll(){
        List<Local> locais = localRepository.findAll();
        return locais;
    }

    public Local salvarLocal(Local local){
        Optional<Local> localOpt = localRepository.findById(local.getId());

        if(localOpt.isEmpty()) throw new RuntimeException();

        return localOpt.get();
    }

    public Local alterarLocal(Local local){
        Optional<Local> localOpt = localRepository.findById(local.getId());

        if(localOpt.isEmpty()) throw new RuntimeException();

        localOpt.get().setNome(local.getNome());
        localOpt.get().setAtivo(local.getAtivo());
        localOpt.get().setDescricao(local.getDescricao());
        localOpt.get().setDataAtualizacao(local.getDataAtualizacao());
        localOpt.get().setDataCriacao(local.getDataCriacao());
        localOpt.get().setDsSiglaAtividade(local.getDsSiglaAtividade());

        return localOpt.get();
    }
}
