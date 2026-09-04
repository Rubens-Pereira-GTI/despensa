package io.github.com.Rubens_Pereira_GTI.despensa.service;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.EstoqueAtualizacaoDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Estoque;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Produto;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.EstoqueRepository;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class EstoqueService {

    private final ProdutoRepository produtoRepository;
    private final EstoqueRepository estoqueRepository;
    

    public EstoqueService(EstoqueRepository estoqueRepository, ProdutoRepository produtoRepository){
        this.estoqueRepository = estoqueRepository;
        this.produtoRepository = produtoRepository;
    }


    @Transactional
    public Estoque salvar(Estoque estoque){
        //verifica se o produto existe
        Optional<Produto> produtoOpt = produtoRepository.findById(estoque.getProdutoId());        
        if(produtoOpt.isEmpty()){
            throw new EntityNotFoundException("Produto não encontrado");
        }
    
        estoque.setProduto(produtoOpt.get());
        return estoqueRepository.save(estoque);
    }


    @Transactional(readOnly = true)
    public Estoque buscarPorId(Long id) {
        Optional<Estoque> estoqueOpt = estoqueRepository.findById(id);
        if(estoqueOpt.isEmpty()){
            throw new EntityNotFoundException("Estoque não encontrado");
        }               
        return estoqueOpt.get();
    }

    //TODO essa busca precisa ser filtrado por local e precisa poder ser filtado pela data de modificação
    @Transactional(readOnly = true)
    public Page<Estoque> buscaPaginada(Integer page, Integer size, Long localId, String sort, String direction){

        
        // Direção padrão: DESC (mais recentes primeiro)
        Sort.Direction direcao = (direction != null && direction.equalsIgnoreCase("ASC")) 
            ? Sort.Direction.ASC : Sort.Direction.DESC;


        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direcao, sort));

        return estoqueRepository.findByProduto_Categoria_Local_Id(localId, pageRequest);
        
        
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
