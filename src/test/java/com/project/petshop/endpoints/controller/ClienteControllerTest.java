package com.project.petshop.endpoints.controller;

import com.project.petshop.creator.ClienteCreator;
import com.project.petshop.creator.ClientePostRequestBodyCreator;
import com.project.petshop.creator.ClientePutRequestBodyCreator;
import com.project.petshop.endpoints.requests.dto.ClientePostRequestBody;
import com.project.petshop.endpoints.requests.dto.ClientePutRequestBody;
import com.project.petshop.endpoints.service.ClienteService;
import com.project.petshop.entity.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@ExtendWith(SpringExtension.class)
@DisplayName("Teste do Controller de Cliente")
class ClienteControllerTest {

    @InjectMocks
    private ClienteController clienteController;
    @Mock
    private ClienteService clienteServiceMock;

    @BeforeEach
    void setUp() {
        BDDMockito.when(clienteServiceMock.save(ArgumentMatchers.any(ClientePostRequestBody.class)))
                .thenReturn(ClienteCreator.createValidCliente());
        BDDMockito.when(clienteServiceMock.update(ArgumentMatchers.any(ClientePutRequestBody.class)))
                .thenReturn(ClienteCreator.createValidCliente());

        List<Cliente> clientes = (Collections.singletonList(ClienteCreator.createCliente()));

        BDDMockito.when(clienteServiceMock.findAll()).thenReturn(clientes);
        BDDMockito.when(clienteServiceMock.findByIdOrThrowBadRequestException(ArgumentMatchers.anyLong()))
                .thenReturn(ClienteCreator.createValidCliente());
        BDDMockito.doNothing().when(clienteServiceMock).delete(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("save retorna Cliente quando com sucesso")
    void save_RetornaCliente_QuandoSucesso(){
        Cliente clienteSaved = clienteController.save(ClientePostRequestBodyCreator.createClientePostRequestBody()).getBody();

        assertThat(clienteSaved)
                .isNotNull()
                .isEqualTo(ClienteCreator.createValidCliente());
    }

    @Test
    @DisplayName("update retorna Cliente quando com sucesso")
    void update_RetornaCliente_QuandoSucesso(){
        ResponseEntity<Cliente> responseEntity = clienteController
                .update(ClientePutRequestBodyCreator.createClientePutRequestBody());

        assertThat(responseEntity.getBody())
                .isNotNull()
                .isEqualTo(ClienteCreator.createValidCliente());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("findAll retorna uma lista de Cliente quando com sucesso")
    void findAll_RetornaListaDeCliente_QuandoSucesso(){
        String expectedName = ClienteCreator.createCliente().getNome();
        List<Cliente> clientes = clienteController.findAll().getBody();

        assertThat(clientes)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        assertThat(clientes.get(0).getNome()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findById retorna um Cliente pelo ID quando com sucesso")
    void findById_RetornaCliente_QuandoSucesso(){
        Long expectedId = ClienteCreator.createValidCliente().getId();
        Cliente schedule = clienteController.findById(1L).getBody();

        assertThat(schedule).isNotNull();
        assertThat(schedule.getId())
                .isNotNull()
                .isEqualTo(expectedId);
    }

    @Test
    @DisplayName("delete remove um Cliente quando com sucesso")
    void delete_RemoveCliente_QuandoSucesso(){

        assertThatCode(() -> clienteController.delete(1L)).doesNotThrowAnyException();

        ResponseEntity<Cliente> entity = clienteController.delete(1L);

        assertThat(entity).isNotNull();
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }


}