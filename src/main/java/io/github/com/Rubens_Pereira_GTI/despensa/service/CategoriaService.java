package io.github.com.Rubens_Pereira_GTI.despensa.service;

import io.github.com.Rubens_Pereira_GTI.despensa.entity.Local;
import io.github.com.Rubens_Pereira_GTI.despensa.exception.OperacaoNaoPermitidaException;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Categoria;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.CategoriaRepository;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.LocalRepository;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.ProdutoRepository;
import io.github.com.Rubens_Pereira_GTI.despensa.validator.CategoriaValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class CategoriaService {


    private final CategoriaRepository categoriaRepository;
    private final CategoriaValidator categoriaValidator;
    private final ProdutoRepository produtoRepository;
    private final LocalRepository localRepository;

    public CategoriaService(CategoriaRepository categoriaRepository,
                            CategoriaValidator categoriaValidator,
                            ProdutoRepository produtoRepository,
                            LocalRepository localRepository) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaValidator = categoriaValidator;
        this.produtoRepository = produtoRepository;
        this.localRepository = localRepository;
    }


    @Transactional(readOnly = true)
    public Categoria buscaCategoriaPorId(Long id){

        Optional<Categoria> categoriaOpt = categoriaRepository.findById(id);
        if(categoriaOpt.isEmpty()){
           throw new EntityNotFoundException("Categoria não encontrada");
        }
        return categoriaOpt.get();
    }

    public Categoria salvarCategoria(Categoria categoria) {
        Optional<Local> localOptional = localRepository.findById(categoria.getLocalId());
        if(localOptional.isEmpty()) throw new EntityNotFoundException("Local não encontrado");
        categoria.setLocal(localOptional.get());
        categoriaValidator.validar(categoria);
        return categoriaRepository.save(categoria);
    }

    @Transactional
    public Categoria alterarCategoria(Categoria categoriaAtualizada, Long id) { 

        Optional<Categoria> categoriaOpt = categoriaRepository.findById(id);
        if (categoriaOpt.isEmpty()) throw new EntityNotFoundException("categoria não encontrada");
        
        categoriaValidator.validar(categoriaAtualizada);

        Categoria categoria = categoriaOpt.get();
        categoria.setNome(categoriaAtualizada.getNome());
        categoria.setDescricao(categoriaAtualizada.getDescricao());
        categoria.setLocalId(categoriaAtualizada.getLocalId());
        categoria.setLocal(categoriaAtualizada.getLocal());
        categoria.setAtivo(categoriaAtualizada.getAtivo());
        
        
        return categoriaRepository.save(categoria);

    }

    public void deletar(Long id) {

        Optional<Categoria> categoriaOpt = categoriaRepository.findById(id);
        if(categoriaOpt.isEmpty()) throw new EntityNotFoundException("Categoria não encontrada");
        Categoria categoria = categoriaOpt.get();

        if(possuiProduto(categoria)){
            throw new OperacaoNaoPermitidaException("Não é permitido excluir categoria que está associada a um produto");
        }
        categoria.setAtivo(false);
        categoriaRepository.save(categoria);
    }

    public boolean possuiProduto(Categoria categoria){
        return produtoRepository.existsByCategoria(categoria);
    }

    public Page<Categoria> pesquisaPaginada(Long localId, 
                                            int page, 
                                            int size, 
                                            String sortField, 
                                            String sortOrder, 
                                            boolean ativo,
                                            String nome) {

        Sort.Direction direction = Sort.Direction.fromString(sortOrder);
        Sort sort = Sort.by(direction, sortField);
        
        Optional<Local> localOpt = localRepository.findById(localId);
        if(localOpt.isEmpty()) throw new EntityNotFoundException("Local não encontrado");

        Categoria categoriaFiltro = new Categoria();
        categoriaFiltro.setAtivo(ativo);
        categoriaFiltro.setLocal(localOpt.get());
        categoriaFiltro.setNome(nome);

        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                                                .withIgnoreCase()
                                                .withIgnoreNullValues()
                                                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        
        Example<Categoria> example = Example.of(categoriaFiltro, exampleMatcher);

        Pageable pageable = PageRequest.of(page, size, sort);

        return categoriaRepository.findAll(example, pageable);
    }
}
