package io.github.com.Rubens_Pereira_GTI.despensa.service;

import io.github.com.Rubens_Pereira_GTI.despensa.entity.Produto;
import io.github.com.Rubens_Pereira_GTI.despensa.exception.OperacaoNaoPermitidaException;
import io.github.com.Rubens_Pereira_GTI.despensa.exception.RegistroDuplicadoException;
import io.github.com.Rubens_Pereira_GTI.despensa.converter.CategoriaConverter;
import io.github.com.Rubens_Pereira_GTI.despensa.dto.CategoriaResponseDto;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Categoria;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.CategoriaRepository;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.LocalRepository;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.ProdutoRepository;
import io.github.com.Rubens_Pereira_GTI.despensa.validator.CategoriaValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaConverter converter;
    private final CategoriaRepository categoriaRepository;
    private final CategoriaValidator categoriaValidator;
    private final ProdutoRepository produtoRepository;

    public CategoriaService(CategoriaConverter converter,
                            CategoriaRepository categoriaRepository,
                            CategoriaValidator categoriaValidator,
                            ProdutoRepository produtoRepository
    ) {
        this.converter = converter;
        this.categoriaRepository = categoriaRepository;
        this.categoriaValidator = categoriaValidator;
        this.produtoRepository = produtoRepository;
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

    public void deletar(Categoria categoria) {

        //TODO talvez pensar em uma regra de negocio, ex não pode ser deletado se tiver produtos nessa categoria
        if(possuiProduto(categoria)){
            throw new OperacaoNaoPermitidaException("Não é permitido excluir categoria que está associada a um produto");
        }

        categoriaRepository.deleteById(categoria.getId());
    }

    public boolean possuiProduto(Categoria categoria){

        Optional<List<Produto>> produtosOpt = produtoRepository.findByCategoria(categoria);

        return produtosOpt.isPresent();

    }
}
