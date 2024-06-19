package com.br.user_service.mapper;

import com.br.user_service.dto.DepartmentDTO;
import com.br.user_service.model.DepartmentModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DepartmentMapper {

    private final ModelMapper modelMapper;

    public DepartmentDTO map(DepartmentModel source) {
        return modelMapper.map(source, DepartmentDTO.class);
    }
}
