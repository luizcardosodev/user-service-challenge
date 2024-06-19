package com.br.user_service.controller;

import com.br.user_service.dto.UserCreateDTO;
import com.br.user_service.dto.UserDTO;
import com.br.user_service.dto.UserUpdateDTO;
import com.br.user_service.interfaces.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Cria um novo usuário")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Departamento não existe", content = @Content),
            @ApiResponse(responseCode = "409", description = "Usuário já cadastrado", content = @Content)})
    public UserDTO create(@RequestBody() @Valid UserCreateDTO dto) {
        return userService.createUser(dto);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Altera um usuário")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Usuário não encontrado", content = @Content)})
    public UserDTO update(@PathVariable() Long id, @RequestBody() UserUpdateDTO dto) {
        return userService.updateUser(id, dto);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Retorna os dados de um usuário")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Usuário não encontrado", content = @Content)})
    public UserDTO getById(@PathVariable() Long id) {
        return userService.findUserById(id);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Exclui um usuário")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Usuário não encontrado", content = @Content)})
    public UserDTO delete(@PathVariable() Long id) {
        return userService.deleteUser(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Retorna todos os usuários criados")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            content = { @Content(mediaType = "application/json")})})
    public List<UserDTO> getAll() {
        return userService.getAllUsers();
    }

}
