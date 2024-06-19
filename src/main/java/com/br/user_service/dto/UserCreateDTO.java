package com.br.user_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserCreateDTO {

    @Schema(description = "E-mail do usuário")
    @Email(message = "E-mail inválido")
    @NotBlank(message = "Field 'email' is required")
    private String email;

    @Schema(description = "Nome do usuário")
    @NotBlank(message = "Field 'name' is required")
    private String name;

    @Schema(description = "ID do departamento que usuário pertence")
    @NotBlank(message = "Field 'departmentId' is required")
    private Long departmentId;
}
