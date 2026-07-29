package io.github.com.Rubens_Pereira_GTI.despensa.service;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.CategoriaDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Local;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Produto;
import io.github.com.Rubens_Pereira_GTI.despensa.exception.OperacaoNaoPermitidaException;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Categoria;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.CategoriaRepository;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.LocalRepository;
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

    //abordagem utilizando o relacionamento entre os objetos  para pegar local
    @Transactional(readOnly = true)
    public Optional<Categoria> buscaCategoriaPorId(Long id){

        Optional<Categoria> categoriaOpt = categoriaRepository.findById(id);

        if(categoriaOpt.isEmpty()){ //
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
        Optional<Local> localOpt = localRepository.findById(categoria.getLocalId());
        if(localOpt.isEmpty()){
            throw new EntityNotFoundException("Local não existe");
        }
        categoria.setLocal(localOpt.get());

        return categoriaRepository.save(categoria);
    }

    @Transactional
    public Categoria atualizarCategoria(CategoriaDTO dto, Long id) {

        categoriaValidator.validar(dto.toCategoria());

        Optional<Categoria> categoriaOpt = categoriaRepository.findById(id);
        if (categoriaOpt.isEmpty()) throw new EntityNotFoundException("categoria não encontrada");

        Optional<Local> localOpt = localRepository.findById(dto.localId());
        if(localOpt.isEmpty()) throw new EntityNotFoundException("Local não encontrado");

        Categoria categoria = categoriaOpt.get();
        categoria.setId(id);
        categoria.setNome(dto.nome());
        categoria.setDescricao(dto.descricao());
        categoria.setLocalId(dto.localId());
        categoria.setLocal(localOpt.get());

        return categoriaRepository.save(categoria);

    }

    public void deletar(Categoria categoria) {

        if(possuiProduto(categoria)){
            throw new OperacaoNaoPermitidaException("Não é permitido excluir categoria que está associada a um produto");
        }

        categoriaRepository.deleteById(categoria.getId());
    }

    public boolean possuiProduto(Categoria categoria){
        return produtoRepository.existsByCategoria(categoria);
    }

    public List<Categoria> buscarTodas() {
        return categoriaRepository.findAll();
    }
}
