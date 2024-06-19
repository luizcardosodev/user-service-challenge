package com.br.user_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DepartmentDTO {

    private Long id;
    @Schema(description = "Nome do departamento")
    private String name;
}
