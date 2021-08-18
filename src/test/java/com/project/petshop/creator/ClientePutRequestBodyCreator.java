package com.project.petshop.creator;

import com.project.petshop.endpoints.requests.dto.ClientePutRequestBody;

import java.time.LocalDate;

public class ClientePutRequestBodyCreator {
    public static ClientePutRequestBody createClientePutRequestBody() {
        return ClientePutRequestBody.builder()
                .id(1L)
                .nome("Jhon")
                .sobrenome("Mayer")
                .dataCadastro(LocalDate.now())
                .build();
    }
}
