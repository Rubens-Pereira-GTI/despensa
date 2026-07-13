package io.github.com.Rubens_Pereira_GTI.despensa.service;

import io.github.com.Rubens_Pereira_GTI.despensa.converter.LocalConverter;
import io.github.com.Rubens_Pereira_GTI.despensa.converter.LocalDtoConverter;
import io.github.com.Rubens_Pereira_GTI.despensa.dto.LocalDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Local;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.LocalRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LocalService {

    private final LocalRepository localRepository;

    public LocalService(LocalRepository localRepository ){
        this.localRepository = localRepository;
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

    @Transactional(readOnly = true)
    public Page<Local> buscaPaginada(Pageable pageable){

        return localRepository.findAll(pageable);
    }

    public Local salvarLocal(LocalDTO dto){

        Local local = dto.toLocal();

        return localRepository.save(local);
    }

    @Transactional
    public Local alterarLocal(LocalDTO dto){

        Local local = dto.toLocal();

        local = localRepository.save(local);

        return  local;
    }

    public void deletar(Long id) {
        localRepository.deleteById(id);
    }
}
