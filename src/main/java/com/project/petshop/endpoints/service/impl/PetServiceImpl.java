package com.project.petshop.endpoints.service.impl;

import com.project.petshop.endpoints.requests.dto.PetPostRequestBody;
import com.project.petshop.endpoints.requests.dto.PetPutRequestBody;
import com.project.petshop.endpoints.requests.mapper.PetMapper;
import com.project.petshop.endpoints.service.PetService;
import com.project.petshop.entity.Pet;
import com.project.petshop.exceptions.ResourceNotFoundException;
import com.project.petshop.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    @Override
    public Pet save(PetPostRequestBody petPostRequestBody) {
        log.info("Salvando um pet...");
        Pet pet = PetMapper.INSTANCE.toPet(petPostRequestBody);
        return petRepository.save(pet);
    }

    @Override
    public Pet update(PetPutRequestBody petPutRequestBody) {
        log.info("Alterando um pet...");
        Pet petSalvo = findByIdOrThrowBadRequestException(petPutRequestBody.getId());
        Pet pet = PetMapper.INSTANCE.toPet(petPutRequestBody);
        pet.setId(petSalvo.getId());
        return petRepository.save(pet);
    }

    @Override
    public Pet findByIdOrThrowBadRequestException(Long id) {
        log.info("Procurando um pet pelo id: " + id);
        return petRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Objeto não encontrado! Id: " + id + ", Tipo: " + Pet.class.getName()));
    }

    @Override
    public List<Pet> findAll() {
        log.info("Listando todos os pets...");
        return petRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        Long idToDelete = findByIdOrThrowBadRequestException(id).getId();
        log.info("Deletando um pet pelo id: " + id);
        petRepository.deleteById(idToDelete);
    }

}
