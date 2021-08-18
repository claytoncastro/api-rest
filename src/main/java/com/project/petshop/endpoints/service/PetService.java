package com.project.petshop.endpoints.service;

import com.project.petshop.endpoints.requests.dto.PetPostRequestBody;
import com.project.petshop.endpoints.requests.dto.PetPutRequestBody;
import com.project.petshop.entity.Pet;

import java.util.List;

public interface PetService {
    Pet save(PetPostRequestBody pet);
    Pet update(PetPutRequestBody pet);
    Pet findByIdOrThrowBadRequestException(Long id);
    List<Pet> findAll();
    void delete(Long id);
}
