package com.project.petshop.creator;

import com.project.petshop.endpoints.requests.dto.ClientePostRequestBody;

import java.time.LocalDate;

public class ClientePostRequestBodyCreator {
    public static ClientePostRequestBody createClientePostRequestBody() {
        return ClientePostRequestBody.builder()
                .nome("Jhon")
                .sobrenome("Mayer")
                .dataCadastro(LocalDate.now())
                .build();
    }
}
