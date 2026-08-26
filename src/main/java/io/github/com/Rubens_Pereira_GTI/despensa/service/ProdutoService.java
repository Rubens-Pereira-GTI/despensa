package io.github.com.Rubens_Pereira_GTI.despensa.service;

import io.github.com.Rubens_Pereira_GTI.despensa.repository.LocalRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Categoria;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Produto;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.UnidadeMedida;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.CategoriaRepository;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.ProdutoRepository;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.UnidadeMedidaRepository;
import io.github.com.Rubens_Pereira_GTI.despensa.validator.ProdutoValidator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Predicate;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final UnidadeMedidaRepository unidadeMedidaRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProdutoValidator produtoValidator;

    public ProdutoService(ProdutoRepository produtoRepository,
                          CategoriaRepository categoriaRepository,
                          UnidadeMedidaRepository unidadeMedidaRepository,
                          ProdutoValidator produtoValidator){

        this.produtoRepository = produtoRepository;
        this.unidadeMedidaRepository = unidadeMedidaRepository;
        this.categoriaRepository = categoriaRepository;
        this.produtoValidator = produtoValidator;
    }

    public Produto salvarProduto(Produto produto){   
        
        produtoValidator.validar(produto);

        Categoria categoria = categoriaRepository.findById(produto.getCategoriaId())
            .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));
        produto.setCategoria(categoria);
        
        UnidadeMedida unidadeMedida = unidadeMedidaRepository.findById(produto.getUnidadeMedidaId())
            .orElseThrow(() -> new EntityNotFoundException("Unidade de medida não encontrada"));
        produto.setUnidadeMedida(unidadeMedida);

        produto.setCategoria(categoria);
        produto.setUnidadeMedida(unidadeMedida);        
        
        return produtoRepository.save(produto);
    }


    @Transactional
    public Produto buscarProduto(Long id) {
        Produto produto = produtoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
        
        Categoria categoria = categoriaRepository.findByProdutosContaining(produto)
            .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));

        produto.setCategoriaId(categoria.getId());
        produto.setCategoria(categoria);

        return produto;
    }

    public Page<Produto> buscarTodos(   Integer page, 
                                        Integer size, 
                                        String sort, 
                                        Long localId, 
                                        String nome, 
                                        Boolean ativo) {

        Specification<Produto> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Encadeamento: Produto -> Categoria -> Local
            if (localId != null) {
                predicates.add(cb.equal(root.join("categoria").join("local").get("id"), localId));
            }

            // Filtro por Nome (LIKE case-insensitive)
            if (nome != null && !nome.isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%"));
            }

            // Filtro por Ativo
            if (ativo != null) {
                predicates.add(cb.equal(root.get("ativo"), ativo));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        Sort ordenacao = (sort != null && !sort.isBlank()) ? Sort.by(sort) : Sort.unsorted();
        PageRequest pageable = PageRequest.of(page, size, ordenacao);

        return produtoRepository.findAll(spec, pageable);
    }

    @Transactional
    public Produto atualizar(Produto proAtualizado, Long id) {

        Produto produto = buscarProduto(id);

        produto.setNome(proAtualizado.getNome());
        produto.setDescricao(proAtualizado.getDescricao());
        produto.setEstoqueMinimo(proAtualizado.getEstoqueMinimo());
        produto.setAtivo(proAtualizado.isAtivo());
        produto.setLocalizacao(proAtualizado.getLocalizacao());

        Optional<Categoria> categoriaOpt = categoriaRepository.findById(proAtualizado.getCategoriaId());
        produto.setCategoria(categoriaOpt.get());
        
        Optional<UnidadeMedida> unidadeMedidaOpt = unidadeMedidaRepository.findById(proAtualizado.getUnidadeMedidaId());
        produto.setUnidadeMedida(unidadeMedidaOpt.get());

        produtoValidator.validar(produto);
        return produtoRepository.save(produto);
    }

    public void deletar(Produto produto) {
        if(!produtoRepository.existsById(produto.getId())){
            throw new EntityNotFoundException("Produto não encontrado");
        }
        produto.setAtivo(false);
        produtoRepository.save(produto);
    }



}
