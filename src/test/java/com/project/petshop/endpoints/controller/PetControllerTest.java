package com.project.petshop.endpoints.controller;

import com.project.petshop.creator.PetCreator;
import com.project.petshop.creator.PetPostRequestBodyCreator;
import com.project.petshop.creator.PetPutRequestBodyCreator;
import com.project.petshop.endpoints.requests.dto.PetPostRequestBody;
import com.project.petshop.endpoints.requests.dto.PetPutRequestBody;
import com.project.petshop.endpoints.service.PetService;
import com.project.petshop.entity.Pet;
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
@DisplayName("Teste do Controller de Pet")
class PetControllerTest {

    @InjectMocks
    private PetController petController;
    @Mock
    private PetService petServiceMock;

    @BeforeEach
    void setUp() {
        BDDMockito.when(petServiceMock.save(ArgumentMatchers.any(PetPostRequestBody.class)))
                .thenReturn(PetCreator.createValidPet());
        BDDMockito.when(petServiceMock.update(ArgumentMatchers.any(PetPutRequestBody.class)))
                .thenReturn(PetCreator.createValidPet());

        List<Pet> pet = (Collections.singletonList(PetCreator.createPet()));

        BDDMockito.when(petServiceMock.findAll()).thenReturn(pet);
        BDDMockito.when(petServiceMock.findByIdOrThrowBadRequestException(ArgumentMatchers.anyLong()))
                .thenReturn(PetCreator.createValidPet());
        BDDMockito.doNothing().when(petServiceMock).delete(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("save retorna Pet quando com sucesso")
    void save_RetornaPet_QuandoSucesso(){
        Pet petSaved = petController.save(PetPostRequestBodyCreator.createPetPostRequestBody()).getBody();

        assertThat(petSaved)
                .isNotNull()
                .isEqualTo(PetCreator.createValidPet());
    }

    @Test
    @DisplayName("update retorna Pet quando com sucesso")
    void update_RetornaPet_QuandoSucesso(){
        ResponseEntity<Pet> responseEntity = petController
                .update(PetPutRequestBodyCreator.createPetPutRequestBody());

        assertThat(responseEntity.getBody())
                .isNotNull()
                .isEqualTo(PetCreator.createValidPet());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("findAll retorna uma lista de Pet quando com sucesso")
    void findAll_RetornaListaDePet_QuandoSucesso(){
        String expectedName = PetCreator.createPet().getNome();
        List<Pet> pets = petController.findAll().getBody();

        assertThat(pets)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        assertThat(pets.get(0).getNome()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findById retorna um Pet pelo ID quando com sucesso")
    void findById_RetornaPet_QuandoSucesso(){
        Long expectedId = PetCreator.createValidPet().getId();
        Pet pet = petController.findById(1L).getBody();

        assertThat(pet).isNotNull();
        assertThat(pet.getId())
                .isNotNull()
                .isEqualTo(expectedId);
    }

    @Test
    @DisplayName("delete remove um Pet quando com sucesso")
    void delete_RemovePet_QuandoSucesso(){

        assertThatCode(() -> petController.delete(1L)).doesNotThrowAnyException();

        ResponseEntity<Pet> entity = petController.delete(1L);

        assertThat(entity).isNotNull();
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }


}