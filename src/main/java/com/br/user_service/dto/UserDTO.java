package com.br.user_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;
    @Schema(description = "Nome do usuário")
    private String name;
    @Schema(description = "Email do usuário")
    private String email;
    @Schema(description = "Departamento do usuário")
    private DepartmentDTO department;
    private Boolean deleted;
}
