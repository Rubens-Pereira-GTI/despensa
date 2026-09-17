package io.github.com.Rubens_Pereira_GTI.despensa.service;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.EstoqueAtualizacaoDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Estoque;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Produto;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.EstoqueRepository;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EstoqueService {

    private final ProdutoRepository produtoRepository;
    private final EstoqueRepository estoqueRepository;
    
    public EstoqueService(EstoqueRepository estoqueRepository, ProdutoRepository produtoRepository){
        this.estoqueRepository = estoqueRepository;
        this.produtoRepository = produtoRepository;
    }

    //TODO retirar metodo
    @Transactional
    public void salvar(Estoque estoqueAtualizado){
        //TODO fazer soma do estoque através de uma movimentação
        //TODO validar se produto e local já existe no estoque

        Optional<Produto> produtoOpt = produtoRepository.findById(estoqueAtualizado.getProdutoId());        
        if(produtoOpt.isEmpty()){
            throw new EntityNotFoundException("Produto não encontrado");
        }

        Optional<Estoque> estoqueOpt = estoqueRepository.findByProduto_Id(estoqueAtualizado.getProdutoId());

        if(estoqueOpt.isEmpty()){
            estoqueRepository.save(estoqueAtualizado);
        }
        
        Estoque estoque = estoqueOpt.get();
        estoque.setDataValidade(estoqueAtualizado.getDataValidade());
        estoque.setLocalizacao(estoqueAtualizado.getLocalizacao());
        estoque.setQtdReservada(estoqueAtualizado.getQtdReservada());
        // o metodo add ja faz a soma  com o bigdecimal.
        estoque.setQuantidade(estoqueAtualizado.getQuantidade());
        estoque.setProduto(produtoOpt.get());
        
        estoqueRepository.save(estoque);
    }

    @Transactional(readOnly = true)
    public Estoque buscarPorId(Long id) {
        Optional<Estoque> estoqueOpt = estoqueRepository.findById(id);
        if(estoqueOpt.isEmpty()){
            throw new EntityNotFoundException("Estoque não encontrado");
        }               
        return estoqueOpt.get();
    }

    @Transactional(readOnly = true)
    public Page<Estoque> buscaPaginada(Integer page, Integer size, Long localId, String sort, String direction, String nome){

        Sort.Direction direcao = (direction != null && direction.equalsIgnoreCase("ASC")) 
            ? Sort.Direction.ASC : Sort.Direction.DESC;

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direcao, sort));

        Specification<Estoque> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Filtro por local: Estoque -> Produto -> Categoria -> Local -> id
            if (localId != null) {
                predicates.add(cb.equal(
                    root.join("produto")
                        .join("categoria")
                        .join("local")
                        .get("id"),
                    localId
                ));
            }

            // Filtro por nome do produto (LIKE case-insensitive)
            if (nome != null && !nome.isBlank()) {
                predicates.add(cb.like(
                    cb.lower(root.join("produto").get("nome")),
                    "%" + nome.toLowerCase() + "%"
                ));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return estoqueRepository.findAll(spec, pageRequest);
    }


    @Transactional
    public void atualizar(EstoqueAtualizacaoDTO dto, Long idEstoque){

        Optional<Estoque> estoqueOpt = estoqueRepository.findById(idEstoque);

        if(estoqueOpt.isEmpty()){
            throw new EntityNotFoundException("Estoque não encontrado");
        }

        Estoque estoque = estoqueOpt.get();        
        estoque.setLocalizacao(dto.localizacao());
        estoque.setDataValidade(dto.dataValidade());
        estoqueRepository.save(estoque);
    }

    
        

}
