package io.github.com.Rubens_Pereira_GTI.despensa.mapper;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.CategoriaDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.dto.CategoriaResponseDto;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Categoria;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Local;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.LocalRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {
    private final LocalRepository localRepository;
    private final LocalMapper localMapper;

    public CategoriaMapper(LocalRepository localRepository, LocalMapper localMapper){
        this.localRepository = localRepository;
        this.localMapper = localMapper;
    }

    public Categoria toEntity(CategoriaDTO dto) {
        if (dto == null) {
            return null;
        }
        Optional<Local> localOptional = localRepository.findById(dto.localId());
        if(localOptional.isEmpty()) throw new EntityNotFoundException("Local não encontrado");
        
        Categoria categoria = new Categoria();
        categoria.setId(dto.id());
        categoria.setNome(dto.nome());
        categoria.setDescricao(dto.descricao());
        categoria.setLocalId(dto.localId());
        categoria.setLocal(localOptional.get());
        categoria.setAtivo(dto.ativo());
        return categoria;
    }

    public CategoriaDTO toDTO(Categoria entity) {
        if (entity == null) {
            return null;
        }
        Long localId = entity.getLocalId() != null 
                ? entity.getLocalId() 
                : (entity.getLocal() != null ? entity.getLocal().getId() : null);

        return new CategoriaDTO(
                entity.getId(),
                entity.getNome(),
                entity.getDescricao(),
                localId,
                entity.getAtivo()
        );
    }

    public CategoriaResponseDto toResponseDTO(Categoria entity) {
        if (entity == null) {
            return null;
        }

        return new CategoriaResponseDto(
                entity.getId(),
                entity.getNome(),
                entity.getDescricao(),
                localMapper.toDTO(entity.getLocal()),
                entity.getAtivo()
        );
    }
}
