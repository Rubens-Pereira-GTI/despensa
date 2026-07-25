package io.github.com.Rubens_Pereira_GTI.despensa.validator;

import io.github.com.Rubens_Pereira_GTI.despensa.entity.Produto;
import io.github.com.Rubens_Pereira_GTI.despensa.exception.RegistroDuplicadoException;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProdutoValidator {
    @Autowired
    private ProdutoRepository produtoRepository;

    public void validar(Produto produto){
        if(existeProdutoCadastrado(produto)){
            throw new RegistroDuplicadoException("Esse produto já existe");
        }
    }

    public boolean existeProdutoCadastrado(Produto produto){
        Optional<Produto> produtoOpt = produtoRepository.findByNomeIgnoreCase(produto.getNome());

        if(produto.getId() == null){
            return produtoOpt.isPresent();
        }
        // se tiver ids iguais retorna falso - essa condição é para atualizações de entidade
        return produtoOpt.isPresent() && !produto.getId().equals(produtoOpt.get().getId());
    }
}
