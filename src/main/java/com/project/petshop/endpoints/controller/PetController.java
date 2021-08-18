package com.project.petshop.endpoints.controller;

import com.project.petshop.endpoints.requests.dto.PetPostRequestBody;
import com.project.petshop.endpoints.requests.dto.PetPutRequestBody;
import com.project.petshop.endpoints.service.PetService;
import com.project.petshop.entity.Pet;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping(path = "v1/pet")
public class PetController {

    private final PetService petService;

    @PostMapping
    @Operation(
            summary = "Salvar um pet",
            description = "Esse método é responsável por salvar um pet")
    public ResponseEntity<Pet> save(@Valid @RequestBody PetPostRequestBody pet) {
        return new ResponseEntity<>(petService.save(pet), HttpStatus.CREATED);
    }

    @PutMapping
    @Operation(
            summary = "Alterar um pet",
            description = "Esse método é responsável por alterar um pet")
    public ResponseEntity<Pet> update(@Valid @RequestBody PetPutRequestBody pet) {
        return new ResponseEntity<>(petService.update(pet), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    @Operation(
            summary = "Achar um pet pelo id",
            description = "Esse método é responsável por achar um pet pelo id")
    public ResponseEntity<Pet> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(petService.findByIdOrThrowBadRequestException(id), HttpStatus.FOUND);
    }

    @GetMapping
    @Operation(
            summary = "Listar todos os pets",
            description = "Esse método é responsável por listar todos os pets")
    public ResponseEntity<List<Pet>> findAll() {
        return new ResponseEntity<>(petService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    @Operation(
            summary = "Deletar um pet pelo id",
            description = "Esse método é responsável por deletar um pet")
    public ResponseEntity<Pet> delete(@PathVariable Long id) {
        petService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
