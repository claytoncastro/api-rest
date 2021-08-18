package com.project.petshop.creator;

import com.project.petshop.endpoints.requests.dto.PetPostRequestBody;

import java.time.LocalDate;

public class PetPostRequestBodyCreator {
    public static PetPostRequestBody createPetPostRequestBody() {
        return PetPostRequestBody.builder()
                .nome("Astor")
                .raca("Schnauzer")
                .dataCadastro(LocalDate.now())
                .build();
    }
}
