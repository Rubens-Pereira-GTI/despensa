package io.github.com.Rubens_Pereira_GTI.despensa.service;

import io.github.com.Rubens_Pereira_GTI.despensa.dto.LocalDTO;
import io.github.com.Rubens_Pereira_GTI.despensa.entity.Local;
import io.github.com.Rubens_Pereira_GTI.despensa.exception.OperacaoNaoPermitidaException;
import io.github.com.Rubens_Pereira_GTI.despensa.exception.RegistroDuplicadoException;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.CategoriaRepository;
import io.github.com.Rubens_Pereira_GTI.despensa.repository.LocalRepository;
import io.github.com.Rubens_Pereira_GTI.despensa.validator.LocalValidator;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LocalServiceTest {

    @Mock
    private LocalRepository localRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private LocalValidator localValidator;

    @InjectMocks
    private LocalService localService;

    @Test
    void deveRetornarLocalQuandoIdExiste() {
        // Arrange
        Long id = 1L;
        Local local = new Local();
        local.setId(id);
        local.setNome("Cozinha");
        local.setAtivo(true);

        when(localRepository.findById(id)).thenReturn(Optional.of(local));

        // Act
        Local resultado = localService.buscarPorId(id);

        // Assert
        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        assertEquals("Cozinha", resultado.getNome());
        verify(localRepository, times(1)).findById(id);
    }

    @Test
    void deveLancarExcecaoQuandoIdNaoExiste() {
        // Arrange
        Long id = 99L;
        when(localRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> localService.buscarPorId(id));

        assertEquals("O local não existe", exception.getMessage());
        verify(localRepository, times(1)).findById(id);
    }

    @Test
    void deveRetornarListaDeTodosOsLocais() {
        Local local1 = new Local();
        local1.setId(1L);
        local1.setNome("Cozinha");
        local1.setAtivo(true);

        Local local2 = new Local();
        local2.setId(2L);
        local2.setNome("Despensa");
        local2.setAtivo(true);

        List<Local> locais = List.of(local1, local2);
        when(localRepository.findAll()).thenReturn(locais);

        List<Local> resultado = localService.findAll();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Cozinha", resultado.get(0).getNome());
        assertEquals("Despensa", resultado.get(1).getNome());
        verify(localRepository, times(1)).findAll();
    }

    @Test
    void deveSalvarLocalComSucesso() {
        Local local = new Local();
        local.setNome("Cozinha");
        local.setAtivo(true);

        when(localRepository.save(local)).thenReturn(local);

        Local resultado = localService.salvar(local);

        assertNotNull(resultado);
        assertEquals("Cozinha", resultado.getNome());
        verify(localValidator).validar(local);
        verify(localRepository).save(local);
    }

    @Test
    void deveLancarExcecaoAoSalvarLocalDuplicado() {
        Local local = new Local();
        local.setNome("Cozinha");

        doThrow(new RegistroDuplicadoException("Esse local já existe"))
                .when(localValidator).validar(local);

        assertThrows(RegistroDuplicadoException.class,
                () -> localService.salvar(local));

        verify(localValidator).validar(local);
        verify(localRepository, never()).save(any());
    }

    @Test
    void deveAlterarLocalComSucesso() {
        Long id = 1L;
        Local localExistente = new Local();
        localExistente.setId(id);
        localExistente.setNome("Nome Antigo");
        localExistente.setDescricao("Desc Antiga");
        localExistente.setAtivo(true);

        LocalDTO dto = new LocalDTO(id, "Nome Novo", "Desc Nova", false);

        when(localRepository.findById(id)).thenReturn(Optional.of(localExistente));
        when(localRepository.save(localExistente)).thenReturn(localExistente);

        Local resultado = localService.alterar(id, dto);

        assertNotNull(resultado);
        assertEquals("Nome Novo", resultado.getNome());
        assertEquals("Desc Nova", resultado.getDescricao());
        assertEquals(false, resultado.getAtivo());
        verify(localValidator).validar(localExistente);
        verify(localRepository).save(localExistente);
    }

    @Test
    void deveLancarExcecaoAoAlterarLocalInexistente() {
        Long id = 99L;
        LocalDTO dto = new LocalDTO(id, "Nome", "Desc", true);

        when(localRepository.findById(id)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> localService.alterar(id, dto));

        assertEquals("Local não encontrado", exception.getMessage());
        verify(localRepository).findById(id);
        verify(localRepository, never()).save(any());
    }

    @Test
    void deveDeletarLocalComSucesso() {
        Long id = 1L;
        Local local = new Local();
        local.setId(id);
        local.setNome("Cozinha");

        when(localRepository.findById(id)).thenReturn(Optional.of(local));
        when(categoriaRepository.existsByLocal(local)).thenReturn(false);

        localService.deletar(id);

        verify(localRepository).findById(id);
        verify(categoriaRepository).existsByLocal(local);
        verify(localRepository).deleteById(id);
    }

    @Test
    void deveLancarExcecaoAoDeletarLocalComCategoriasVinculadas() {
        Long id = 1L;
        Local local = new Local();
        local.setId(id);
        local.setNome("Cozinha");

        when(localRepository.findById(id)).thenReturn(Optional.of(local));
        when(categoriaRepository.existsByLocal(local)).thenReturn(true);

        OperacaoNaoPermitidaException exception = assertThrows(OperacaoNaoPermitidaException.class,
                () -> localService.deletar(id));

        assertEquals("Não é permitido excluir o local com Categorias vinculadas", exception.getMessage());
        verify(localRepository).findById(id);
        verify(categoriaRepository).existsByLocal(local);
        verify(localRepository, never()).deleteById(any());
    }
}