package com.project.petshop.endpoints.service.impl;

import com.project.petshop.endpoints.requests.dto.ClientePostRequestBody;
import com.project.petshop.endpoints.requests.dto.ClientePutRequestBody;
import com.project.petshop.endpoints.requests.mapper.ClienteMapper;
import com.project.petshop.endpoints.service.ClienteService;
import com.project.petshop.entity.Cliente;
import com.project.petshop.exceptions.ResourceNotFoundException;
import com.project.petshop.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public Cliente save(ClientePostRequestBody clientePostRequestBody) {
        log.info("Salvando um cliente...");
        Cliente cliente = ClienteMapper.INSTANCE.toCliente(clientePostRequestBody);
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente update(ClientePutRequestBody clientePutRequestBody) {
        log.info("Alterando um cliente...");
        Cliente clienteSalvo = findByIdOrThrowBadRequestException(clientePutRequestBody.getId());
        Cliente cliente = ClienteMapper.INSTANCE.toCliente(clientePutRequestBody);
        cliente.setId(clienteSalvo.getId());
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente findByIdOrThrowBadRequestException(Long id) {
        log.info("Procurando um cliente pelo id: " + id);
        return clienteRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    @Override
    public List<Cliente> findAll() {
        log.info("Listando todos os clientes...");
        return clienteRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        Long idToDelete = findByIdOrThrowBadRequestException(id).getId();
        log.info("Deletando um cliente pelo id: " + id);
        clienteRepository.deleteById(idToDelete);
    }


}
