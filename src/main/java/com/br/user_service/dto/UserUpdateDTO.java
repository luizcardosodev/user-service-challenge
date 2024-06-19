package com.br.user_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDTO {

    @Schema(description = "Nome do usuário")
    private String name;
    @Schema(description = "Email do usuário")
    private String email;
    @Schema(description = "ID do departamento que usuário pertence")
    private Long departmentId;
}
