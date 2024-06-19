package com.br.user_service.controller;

import com.br.user_service.dto.DepartmentDTO;
import com.br.user_service.interfaces.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/departments")
@AllArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    @Operation(summary = "Retorna todos os departamentos")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json")})})
    public List<DepartmentDTO> getAll() {
        return departmentService.getAll();
    }
}
