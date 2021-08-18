package com.project.petshop.creator;

import com.project.petshop.endpoints.requests.dto.ClientePutRequestBody;
import com.project.petshop.endpoints.requests.dto.PetPutRequestBody;

import java.time.LocalDate;

public class PetPutRequestBodyCreator {
    public static PetPutRequestBody createPetPutRequestBody() {
        return PetPutRequestBody.builder()
                .id(1L)
                .nome("Astor")
                .raca("Schnauzer")
                .dataCadastro(LocalDate.now())
                .build();
    }
}
