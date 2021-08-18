package com.project.petshop.endpoints.service.impl;

import com.project.petshop.creator.PetCreator;
import com.project.petshop.creator.PetPostRequestBodyCreator;
import com.project.petshop.creator.PetPutRequestBodyCreator;
import com.project.petshop.endpoints.requests.dto.PetPostRequestBody;
import com.project.petshop.entity.Pet;
import com.project.petshop.exceptions.ResourceNotFoundException;
import com.project.petshop.repository.PetRepository;
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
@DisplayName("Teste do Service de Pet")
class PetServiceImplTest {

    @InjectMocks
    private PetServiceImpl petService;
    @Mock
    private PetRepository petRepositoryMok;

    @BeforeEach
    void setUp() {
        BDDMockito.when(petRepositoryMok.save(ArgumentMatchers.any(Pet.class)))
                .thenReturn(PetCreator.createValidPet());
        List<Pet> pets = Collections.singletonList(PetCreator.createPet());
        BDDMockito.when(petRepositoryMok.findAll()).thenReturn(pets);
        BDDMockito.when(petRepositoryMok.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(PetCreator.createValidPet()));
        BDDMockito.doNothing().when(petRepositoryMok).delete(ArgumentMatchers.any(Pet.class));
    }

    @Test
    @DisplayName("save retorna Pet quando com sucesso")
    void save_RetornaPet_QuandoSucesso(){
        PetPostRequestBody petToBeSaved = PetPostRequestBodyCreator.createPetPostRequestBody();
        Pet petSaved = petService.save(petToBeSaved);

        assertThat(petSaved)
                .isNotNull()
                .isEqualTo(PetCreator.createValidPet());
        assertThat(petSaved.getNome()).isEqualTo(petToBeSaved.getNome());
        assertThat(petSaved.getRaca()).isEqualTo(petToBeSaved.getRaca());
        assertThat(petSaved.getDataCadastro()).isEqualTo(petToBeSaved.getDataCadastro());

    }

    @Test
    @DisplayName("update retorna Pet quando com sucesso")
    void update_RetornaPet_QuandoSucesso(){
        assertThatCode(() -> petService.update(PetPutRequestBodyCreator.createPetPutRequestBody()))
                .doesNotThrowAnyException();

        Pet responseEntity = petService.update(PetPutRequestBodyCreator.createPetPutRequestBody());
        assertThat(responseEntity)
                .isNotNull()
                .isEqualTo(PetCreator.createValidPet());
    }

    @Test
    @DisplayName("findAll retorna uma lista de Pet quando com sucesso")
    void findAll_RetornaListaDePet_QuandoSucesso(){
        String expectedName = PetCreator.createPet().getNome();
        List<Pet> pets = petService.findAll();

        assertThat(pets)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        assertThat(pets.get(0).getNome()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findById retorna Pet por ID quando com sucesso")
    void findById_RetornaPet_QuandoSucesso(){
        Long expectedId = PetCreator.createValidPet().getId();
        Pet pet = petService.findByIdOrThrowBadRequestException(1L);

        assertThat(pet).isNotNull();
        assertThat(pet.getId())
                .isNotNull()
                .isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findById throw ResourceNotFoundException quando Pet não existe")
    void findById_ThrowResourceNotFoundException_QuandoIdNaoExiste() {
        BDDMockito.when(petRepositoryMok.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(() -> this.petService.findByIdOrThrowBadRequestException(1L));
    }


    @Test
    @DisplayName("delete remove Pet quando com sucesso")
    void delete_RemovePet_QuandoSucesso(){

        assertThatCode(() -> petService.delete(1L)).doesNotThrowAnyException();

    }



}