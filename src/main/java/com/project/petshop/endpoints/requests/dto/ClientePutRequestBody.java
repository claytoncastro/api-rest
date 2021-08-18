package com.project.petshop.endpoints.requests.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientePutRequestBody {

    private Long id;
    @NotBlank(message = "O campo 'nome' é obrigatório")
    private String nome;
    private String sobrenome;
    @NotNull(message = "O campo 'dataCadastro' é obrigatório")
    private LocalDate dataCadastro;

}
