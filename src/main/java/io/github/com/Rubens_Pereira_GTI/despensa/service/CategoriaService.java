package io.github.com.Rubens_Pereira_GTI.despensa.service;

import io.github.com.Rubens_Pereira_GTI.despensa.converter.CategoriaConverter;
import io.github.com.Rubens_Pereira_GTI.despensa.dto.CategoriaRequestDto;
import io.github.com.Rubens_Pereira_GTI.despensa.dto.CategoriaResponseDto;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Categoria;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Local;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.CategoriaRepository;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.LocalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaConverter converter;
    private final CategoriaRepository categoriaRepository;
    private final LocalRepository localRepository;

    public CategoriaService(CategoriaConverter converter,
                            CategoriaRepository categoriaRepository,
                            LocalRepository localRepository) {
        this.converter = converter;
        this.categoriaRepository = categoriaRepository;
        this.localRepository = localRepository;
    }

    @Transactional(readOnly = true)
    public CategoriaResponseDto buscaCategoriaId(Long id){
        Optional<Categoria> categoriaOpt = categoriaRepository.findById(id);
        if(categoriaOpt.isEmpty()){
            throw new EntityNotFoundException("Categoria não encontrada");
        }
        return converter.toResponseDto(categoriaOpt.get());
    }

    @Transactional(readOnly = true)
    public Page<CategoriaResponseDto> buscaTodasCategoriasPaginada(Pageable pageable){
        Page<Categoria> pagina = categoriaRepository.findAll(pageable);
        return pagina.map(converter::toResponseDto);
    }

    public CategoriaResponseDto salvarCategoria(CategoriaRequestDto requestDto) {

        Local local = localRepository.findById(requestDto.localId())
                .orElseThrow(() -> new EntityNotFoundException("Local não encontrado com id: " + requestDto.localId()));

        Categoria categoria = converter.toEntity(requestDto, local);
        categoria = categoriaRepository.save(categoria);

        return converter.toResponseDto(categoria);
    }

    @Transactional
    public CategoriaResponseDto alterarCategoria(Long id, CategoriaRequestDto requestDto) {

        Optional<Categoria> categoriaOpt = categoriaRepository.findById(id);
        if(categoriaOpt.isEmpty()){
            throw new EntityNotFoundException("Categoria não encontrada, id: "+ id );
        }
        Categoria categoria = categoriaOpt.get();

        Local local = localRepository.findById(requestDto.localId())
                .orElseThrow(() -> new EntityNotFoundException("Local não encontrado com id: " + requestDto.localId()));

        Categoria categoriaAtualizada = converter.toEntity(requestDto, local);

        categoria.setNome(categoriaAtualizada.getNome());
        categoria.setDescricao(categoriaAtualizada.getDescricao());
        categoria.setLocal(categoriaAtualizada.getLocal());

        categoria = categoriaRepository.save(categoria);

        return converter.toResponseDto(categoria);

    }

    public void deletar(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new EntityNotFoundException("Categoria não encontrada, ID: " + id);
        }
        categoriaRepository.deleteById(id);
    }
}
