package io.github.com.Rubens_Pereira_GTI.despensa.service;

import io.github.com.Rubens_Pereira_GTI.despensa.exception.RegistroDuplicadoException;
import io.github.com.Rubens_Pereira_GTI.despensa.converter.CategoriaConverter;
import io.github.com.Rubens_Pereira_GTI.despensa.dto.CategoriaResponseDto;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Categoria;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.CategoriaRepository;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.LocalRepository;
import io.github.com.Rubens_Pereira_GTI.despensa.validator.CategoriaValidator;
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
    private final CategoriaValidator categoriaValidator;

    public CategoriaService(CategoriaConverter converter,
                            CategoriaRepository categoriaRepository,
                            CategoriaValidator categoriaValidator
    ) {
        this.converter = converter;
        this.categoriaRepository = categoriaRepository;
        this.categoriaValidator = categoriaValidator;
    }

    @Transactional(readOnly = true)
    public Optional<Categoria> buscaCategoriaPorId(Long id){
        return categoriaRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Page<CategoriaResponseDto> buscaTodasCategoriasPaginada(Pageable pageable){
        Page<Categoria> pagina = categoriaRepository.findAll(pageable);
        return pagina.map(converter::toResponseDto);
    }

    public Categoria salvarCategoria(Categoria categoria) {
        categoriaValidator.validar(categoria);
        return categoriaRepository.save(categoria);
    }

    @Transactional
    public Categoria atualizarCategoria(Categoria categoria) {
        categoriaValidator.validar(categoria);
        return categoriaRepository.save(categoria);

    }

    public void deletar(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new EntityNotFoundException("Categoria não encontrada, ID: " + id);
        }
        categoriaRepository.deleteById(id);
    }
}
