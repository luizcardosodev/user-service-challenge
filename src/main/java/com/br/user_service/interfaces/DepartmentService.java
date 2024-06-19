package com.br.user_service.interfaces;

import com.br.user_service.dto.DepartmentDTO;
import com.br.user_service.model.DepartmentModel;

import java.util.List;

public interface DepartmentService {
    DepartmentModel getDepartment(Long id);
    List<DepartmentDTO> getAll();
}
