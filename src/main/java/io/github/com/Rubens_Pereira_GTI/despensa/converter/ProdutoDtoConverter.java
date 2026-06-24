package io.github.com.Rubens_Pereira_GTI.despensa.converter;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.ProdutoRequestDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Produto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProdutoDtoConverter implements Converter<ProdutoRequestDTO, Produto>{

    @Override
    public Produto convert(ProdutoRequestDTO produtoRequestDTO) {

        if (produtoRequestDTO == null) {
            return null;
        }

        // 4. Instanciação e mapeamento manual dos campos
        Produto produto = new Produto();
        produto.setNome(produtoRequestDTO.nome());
        produto.setDescricao(produtoRequestDTO.descricao());
        produto.setLocalizacao(produtoRequestDTO.localizacao());
        produto.setEstoqueMinimo(produtoRequestDTO.estoqueMinimo());
        produto.setAtivo(produtoRequestDTO.ativo());
        produto.setCategoriaId(produtoRequestDTO.categoriaId());
        produto.setCategoriaId(produtoRequestDTO.categoriaId());
        produto.setUnidadeMedidaId(produtoRequestDTO.unidadeMedidaId());
        produto.setLocalId(produtoRequestDTO.localId());

        // Se houver lógica de negócio simples na conversão, ela entra aqui
        // produto.setAtivo(true);

        return produto;
    }




}
