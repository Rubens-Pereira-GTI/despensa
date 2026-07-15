package io.github.com.Rubens_Pereira_GTI.despensa.service;

import io.github.com.Rubens_Pereira_GTI.despensa.entity.Local;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Produto;
import io.github.com.Rubens_Pereira_GTI.despensa.exception.OperacaoNaoPermitidaException;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Categoria;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.CategoriaRepository;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.ProdutoRepository;
import io.github.com.Rubens_Pereira_GTI.despensa.validator.CategoriaValidator;
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
                            ProdutoRepository produtoRepository
    ) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaValidator = categoriaValidator;
        this.produtoRepository = produtoRepository;
    }

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

        if(possuiProduto(categoria)){
            throw new OperacaoNaoPermitidaException("Não é permitido excluir categoria que está associada a um produto");
        }

        categoriaRepository.deleteById(categoria.getId());
    }

    public boolean possuiProduto(Categoria categoria){
        return produtoRepository.existsByCategoria(categoria);
    }
}
