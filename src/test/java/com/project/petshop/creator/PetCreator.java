package com.project.petshop.creator;

import com.project.petshop.entity.Pet;

import java.time.LocalDate;

public class PetCreator {

    public static Pet createPet() {
        return Pet.builder()
                .nome("Astor")
                .raca("Schnauzer")
                .dataCadastro(LocalDate.now())
                .build();
    }

    public static Pet createValidPet() {
        return Pet.builder()
                .id(1L)
                .nome("Astor")
                .raca("Schnauzer")
                .dataCadastro(LocalDate.now())
                .build();
    }

}
