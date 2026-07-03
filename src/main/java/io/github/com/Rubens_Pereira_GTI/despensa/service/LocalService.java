package io.github.com.Rubens_Pereira_GTI.despensa.service;

import io.github.com.Rubens_Pereira_GTI.despensa.converter.LocalConverter;
import io.github.com.Rubens_Pereira_GTI.despensa.converter.LocalDtoConverter;
import io.github.com.Rubens_Pereira_GTI.despensa.dto.LocalRequestDto;
import io.github.com.Rubens_Pereira_GTI.despensa.dto.LocalResponseDto;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Local;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.LocalRepository;
import org.hibernate.engine.spi.Resolution;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LocalService {

    private final LocalRepository localRepository;
    private final LocalConverter localConverter;
    private final LocalDtoConverter localDtoConverter;

    public LocalService(LocalRepository localRepository,
                        LocalConverter localConverter,
                        LocalDtoConverter localDtoConverter
    ){
        this.localRepository = localRepository;
        this.localConverter = localConverter;
        this.localDtoConverter = localDtoConverter;
    }

    @Transactional(readOnly = true)
    public LocalResponseDto findById(Long id){
        Optional<Local> localOpt = localRepository.findById(id);

        if(localOpt.isEmpty()) throw new RuntimeException("Local não encontrado");

        LocalResponseDto responseDto = localConverter.convert(localOpt.get());

        return responseDto ;
    }

    @Transactional(readOnly = true)
    public List<LocalResponseDto> findAll(){
        List<Local> local = localRepository.findAll();

        List<LocalResponseDto> responseDto = local.stream().map(localConverter::convert).toList();

        return responseDto;
    }

    @Transactional(readOnly = true)
    public Page<LocalResponseDto> buscaPaginada(Pageable pageable){
        Page<Local> pagina = localRepository.findAll(pageable);

        Page<LocalResponseDto> responseDto = pagina.map(localConverter::convert);

        return responseDto;
    }

    public LocalResponseDto salvarLocal(LocalRequestDto requestDto){

        Local local = localDtoConverter.convert(requestDto);

        local = localRepository.save(local);

        return localConverter.convert(local);
    }

    @Transactional
    public LocalResponseDto alterarLocal(LocalRequestDto requestDto, Long id){

        Local local = localDtoConverter.convert(requestDto);

        Optional<Local> localOpt = localRepository.findById(id);

        if(localOpt.isEmpty()) {
            throw new RuntimeException("objeto não encontrado");
        }

        Local localDB = localOpt.get();

        if(local != null){
            localDB.setNome(local.getNome());
            localDB.setDescricao(local.getDescricao());
            localDB.setAtivo(local.getAtivo());
            localDB.setDataAtualizacao(local.getDataAtualizacao());

        }else {
            throw  new RuntimeException("Conversão de local vazia");
        }

        localDB = localRepository.save(localDB);


        return  localConverter.convert(localDB);
    }
}
