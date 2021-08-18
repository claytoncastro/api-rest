package com.project.petshop.creator;

import com.project.petshop.entity.Cliente;

import java.time.LocalDate;

public class ClienteCreator {

    public static Cliente createCliente() {
        return Cliente.builder()
                .nome("Jhon")
                .sobrenome("Mayer")
                .dataCadastro(LocalDate.now())
                .build();
    }

    public static Cliente createValidCliente() {
        return Cliente.builder()
                .id(1L)
                .nome("Jhon")
                .sobrenome("Mayer")
                .dataCadastro(LocalDate.now())
                .build();
    }

}
