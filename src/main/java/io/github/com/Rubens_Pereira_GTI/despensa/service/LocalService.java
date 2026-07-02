package io.github.com.Rubens_Pereira_GTI.despensa.service;

import io.github.com.Rubens_Pereira_GTI.despensa.converter.LocalConverter;
import io.github.com.Rubens_Pereira_GTI.despensa.dto.LocalRequestDto;
import io.github.com.Rubens_Pereira_GTI.despensa.dto.LocalResponseDto;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Local;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.LocalRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LocalService {

    private final LocalRepository localRepository;
    private final LocalConverter localConverter;

    public LocalService(LocalRepository localRepository, LocalConverter localConverter){
        this.localRepository = localRepository;
        this.localConverter = localConverter;
    }

    @Transactional(readOnly = true)
    public Local findById(Long id){
        Optional<Local> localOpt = localRepository.findById(id);

        if(localOpt.isEmpty()) throw new RuntimeException("Local não encontrado");

        return localOpt.get() ;
    }

    public List<LocalResponseDto> findAll(){
        List<Local> local = localRepository.findAll();

        List<LocalResponseDto> responseDto = local.stream().map(localConverter::convert).toList();

        return responseDto;
    }

    public Page<Local> buscaPaginada(Pageable pageable){
        Page<Local> pagina = localRepository.findAll(pageable);

        Page<LocalResponseDto> responseDto = pagina.map(localConverter::convert);

        return pagina;
    }

    public LocalResponseDto salvarLocal(Local local){

        local = localRepository.save(local);

        return localConverter.convert(local);
    }

    @Transactional
    public LocalResponseDto alterarLocal(Local local){
        Optional<Local> localOpt = localRepository.findById(local.getId());

        if(localOpt.isEmpty()) throw new RuntimeException("objeto não encontrado");

        Local locAtualizado = localOpt.get();

        locAtualizado.setNome(local.getNome());
        locAtualizado.setAtivo(local.getAtivo());
        locAtualizado.setDescricao(local.getDescricao());
        locAtualizado.setDataAtualizacao(local.getDataAtualizacao());

        return  localConverter.convert(locAtualizado);
    }
}
