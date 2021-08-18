package com.project.petshop.endpoints.controller;

import com.project.petshop.endpoints.requests.dto.ClientePostRequestBody;
import com.project.petshop.endpoints.requests.dto.ClientePutRequestBody;
import com.project.petshop.endpoints.service.ClienteService;
import com.project.petshop.entity.Cliente;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "v1/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> save(@Valid @RequestBody ClientePostRequestBody cliente) {
        return new ResponseEntity<>(clienteService.save(cliente), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Cliente> update(@Valid @RequestBody ClientePutRequestBody cliente) {
        return new ResponseEntity<>(clienteService.update(cliente), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(clienteService.findByIdOrThrowBadRequestException(id), HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> findAll() {
        return new ResponseEntity<>(clienteService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Cliente> delete(@PathVariable Long id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
