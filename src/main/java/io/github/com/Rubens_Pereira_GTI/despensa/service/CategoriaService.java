package io.github.com.Rubens_Pereira_GTI.despensa.service;

import io.github.com.Rubens_Pereira_GTI.despensa.entity.Local;
import io.github.com.Rubens_Pereira_GTI.despensa.exception.OperacaoNaoPermitidaException;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Categoria;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.CategoriaRepository;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.ProdutoRepository;
import io.github.com.Rubens_Pereira_GTI.despensa.validator.CategoriaValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {


    private final CategoriaRepository categoriaRepository;
    private final CategoriaValidator categoriaValidator;
    private final ProdutoRepository produtoRepository;

    public CategoriaService(CategoriaRepository categoriaRepository,
                            CategoriaValidator categoriaValidator,
                            ProdutoRepository produtoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaValidator = categoriaValidator;
        this.produtoRepository = produtoRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Categoria> buscaCategoriaPorId(Long id){

        Optional<Categoria> categoriaOpt = categoriaRepository.findById(id);

        if(categoriaOpt.isEmpty()){
            return categoriaOpt;
        }

        Local local = categoriaOpt.get().getLocal();
        categoriaOpt.get().setLocalId(local.getId());

        return categoriaOpt;
    }

    @Transactional(readOnly = true)
    public Optional<Categoria> buscaCategoria(Long id){

        Optional<Categoria> categoriaOpt = categoriaRepository.findById(id);
        Long localId = categoriaRepository.findLocalIdByCategoriaId(id);

        if(categoriaOpt.isEmpty()){
           return categoriaOpt;
        }
        categoriaOpt.get().setLocalId(localId);
        return categoriaOpt;
    }

    public Categoria salvarCategoria(Categoria categoria) {
        categoriaValidator.validar(categoria);
        return categoriaRepository.save(categoria);
    }

    @Transactional
    public Categoria atualizarCategoria(Categoria categoriaAtualizada, Long id) { 

        Optional<Categoria> categoriaOpt = categoriaRepository.findById(id);
        if (categoriaOpt.isEmpty()) throw new EntityNotFoundException("categoria não encontrada");

        Categoria categoria = categoriaOpt.get();
        categoria.setNome(categoriaAtualizada.getNome());
        categoria.setDescricao(categoriaAtualizada.getDescricao());
        categoria.setLocalId(categoriaAtualizada.getLocalId());
        categoria.setLocal(categoriaAtualizada.getLocal());
        categoria.setAtivo(categoriaAtualizada.getAtivo());
        
        categoriaValidator.validar(categoria);
        return categoriaRepository.save(categoria);

    }

    public void deletar(Categoria categoria) {

        if(possuiProduto(categoria)){
            throw new OperacaoNaoPermitidaException("Não é permitido excluir categoria que está associada a um produto");
        }
        categoria.setAtivo(false);
        categoriaRepository.save(categoria);
    }

    public boolean possuiProduto(Categoria categoria){
        return produtoRepository.existsByCategoria(categoria);
    }

    public List<Categoria> buscarTodas() {
        return categoriaRepository.findAll();
    }
}
