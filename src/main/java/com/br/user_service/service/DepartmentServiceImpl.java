package com.br.user_service.service;

import com.br.user_service.dto.DepartmentDTO;
import com.br.user_service.exception.BusinessServiceException;
import com.br.user_service.interfaces.DepartmentService;
import com.br.user_service.mapper.DepartmentMapper;
import com.br.user_service.model.DepartmentModel;
import com.br.user_service.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.br.user_service.exception.enumeration.ErrorApiCode.DEPARTMENT_NOT_FOUND;
import static com.br.user_service.helper.MessageHelper.getMessage;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper mapper;

    @Override
    public DepartmentModel getDepartment(Long id) {
        Optional<DepartmentModel> departmentModel = departmentRepository.findById(id);
        if (departmentModel.isEmpty()) {
            log.error(getMessage(DEPARTMENT_NOT_FOUND, id.toString()));
            throw new BusinessServiceException(NOT_FOUND, getMessage(DEPARTMENT_NOT_FOUND, id.toString()));
        }
        return departmentModel.get();
    }

    @Override
    public List<DepartmentDTO> getAll() {
        return departmentRepository
                .findAll().stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }
}
