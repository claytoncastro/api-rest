package com.project.petshop.endpoints.service;

import com.project.petshop.endpoints.requests.dto.ClientePostRequestBody;
import com.project.petshop.endpoints.requests.dto.ClientePutRequestBody;
import com.project.petshop.entity.Cliente;

import java.util.List;

public interface ClienteService {

    Cliente save(ClientePostRequestBody cliente);
    Cliente update(ClientePutRequestBody cliente);
    Cliente findByIdOrThrowBadRequestException(Long id);
    List<Cliente> findAll();
    void delete(Long id);

}
