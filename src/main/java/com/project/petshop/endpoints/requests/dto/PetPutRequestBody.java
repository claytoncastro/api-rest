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
public class PetPutRequestBody {

    private Long id;
    @Schema(description = "Esse é o nome do pet" , example = "Astor")
    @NotBlank(message = "O campo 'nome' é obrigatório")
    private String nome;
    @Schema(description = "Essa é a raça do pet" , example = "Schnauzer")
    @NotBlank(message = "O campo 'raca' é obrigatório")
    private String raca;
    @Schema(description = "Esse a data de cadastro do pet e deve ser no formato 'yyyy-MM-dd'" ,
            example = "2021-08-18")
    @NotNull(message = "O campo 'dataCadastro' é obrigatório")
    private LocalDate dataCadastro;

}
