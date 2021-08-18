package com.project.petshop.endpoints.service.impl;

import com.project.petshop.creator.ClienteCreator;
import com.project.petshop.creator.ClientePostRequestBodyCreator;
import com.project.petshop.creator.ClientePutRequestBodyCreator;
import com.project.petshop.endpoints.requests.dto.ClientePostRequestBody;
import com.project.petshop.entity.Cliente;
import com.project.petshop.exceptions.ResourceNotFoundException;
import com.project.petshop.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@ExtendWith(SpringExtension.class)
@DisplayName("Teste do Service de Cliente")
class ClienteServiceImplTest {

    @InjectMocks
    private ClienteServiceImpl clienteService;
    @Mock
    private ClienteRepository clienteRepositoryMok;

    @BeforeEach
    void setUp() {
        BDDMockito.when(clienteRepositoryMok.save(ArgumentMatchers.any(Cliente.class)))
                .thenReturn(ClienteCreator.createValidCliente());
        List<Cliente> clientes = Collections.singletonList(ClienteCreator.createCliente());
        BDDMockito.when(clienteRepositoryMok.findAll()).thenReturn(clientes);
        BDDMockito.when(clienteRepositoryMok.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(ClienteCreator.createValidCliente()));
        BDDMockito.doNothing().when(clienteRepositoryMok).delete(ArgumentMatchers.any(Cliente.class));
    }

    @Test
    @DisplayName("save retorna Cliente quando com sucesso")
    void save_RetornaCliente_QuandoSucesso(){
        ClientePostRequestBody clienteToBeSaved = ClientePostRequestBodyCreator.createClientePostRequestBody();
        Cliente clienteSaved = clienteService.save(clienteToBeSaved);

        assertThat(clienteSaved)
                .isNotNull()
                .isEqualTo(ClienteCreator.createValidCliente());
        assertThat(clienteSaved.getNome()).isEqualTo(clienteToBeSaved.getNome());
        assertThat(clienteSaved.getSobrenome()).isEqualTo(clienteToBeSaved.getSobrenome());
        assertThat(clienteSaved.getDataCadastro()).isEqualTo(clienteToBeSaved.getDataCadastro());

    }

    @Test
    @DisplayName("update retorna Cliente quando com sucesso")
    void update_RetornaCliente_QuandoSucesso(){
        assertThatCode(() -> clienteService.update(ClientePutRequestBodyCreator.createClientePutRequestBody()))
                .doesNotThrowAnyException();

        Cliente responseEntity = clienteService.update(ClientePutRequestBodyCreator.createClientePutRequestBody());
        assertThat(responseEntity)
                .isNotNull()
                .isEqualTo(ClienteCreator.createValidCliente());
    }

    @Test
    @DisplayName("findAll retorna uma lista de Cliente quando com sucesso")
    void findAll_RetornaListaDeCliente_QuandoSucesso(){
        String expectedName = ClienteCreator.createCliente().getNome();
        List<Cliente> clientes = clienteService.findAll();

        assertThat(clientes)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        assertThat(clientes.get(0).getNome()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findById retorna Cliente por ID quando com sucesso")
    void findById_RetornaCliente_QuandoSucesso(){
        Long expectedId = ClienteCreator.createValidCliente().getId();
        Cliente cliente = clienteService.findByIdOrThrowBadRequestException(1L);

        assertThat(cliente).isNotNull();
        assertThat(cliente.getId())
                .isNotNull()
                .isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findById throw ResourceNotFoundException quando CLiente não existe")
    void findById_ThrowResourceNotFoundException_QuandoIdNaoExiste() {
        BDDMockito.when(clienteRepositoryMok.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(() -> this.clienteService.findByIdOrThrowBadRequestException(1L));
    }


    @Test
    @DisplayName("delete remove Cliente quando com sucesso")
    void delete_RemoveCliente_QuandoSucesso(){

        assertThatCode(() -> clienteService.delete(1L)).doesNotThrowAnyException();

    }



}