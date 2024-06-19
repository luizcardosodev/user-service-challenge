package com.br.user_service.mapper;

import com.br.user_service.dto.UserCreateDTO;
import com.br.user_service.dto.UserDTO;
import com.br.user_service.dto.UserUpdateDTO;
import com.br.user_service.model.DepartmentModel;
import com.br.user_service.model.UserModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMapper {
    private final ModelMapper modelMapper;

    public UserDTO map(UserModel source) {
        return modelMapper.map(source, UserDTO.class);
    }

    public UserModel map(UserCreateDTO source, DepartmentModel departmentModel) {
        return UserModel.builder()
                .name(source.getName())
                .email(source.getEmail())
                .department(departmentModel)
                .deleted(false)
                .build();
    }

    public UserModel map(UserModel userModel, UserUpdateDTO dto, DepartmentModel departmentModel) {
        return UserModel.builder()
                .id(userModel.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .department(departmentModel)
                .deleted(userModel.getDeleted())
                .build();
    }
}
