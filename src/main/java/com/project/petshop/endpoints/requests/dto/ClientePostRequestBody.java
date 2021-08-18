package com.project.petshop.endpoints.requests.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class ClientePostRequestBody {

    @Schema(description = "Esse é o nome do cliente" , example = "João")
    @NotBlank(message = "O campo 'nome' é obrigatório")
    private String nome;
    @Schema(description = "Esse é o sobrenome do cliente" , example = "Pereira")
    private String sobrenome;
    @Schema(description = "Esse a data de cadastro do cliente e deve ser no formato 'yyyy-MM-dd'" ,
            example = "2021-08-18")
    @NotNull(message = "O campo 'dataCadastro' é obrigatório")
    private LocalDate dataCadastro;

}
