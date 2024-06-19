package com.br.user_service.service;

import com.br.user_service.dto.UserCreateDTO;
import com.br.user_service.dto.UserDTO;
import com.br.user_service.dto.UserUpdateDTO;
import com.br.user_service.exception.BusinessServiceException;
import com.br.user_service.interfaces.DepartmentService;
import com.br.user_service.interfaces.UserService;
import com.br.user_service.mapper.UserMapper;
import com.br.user_service.model.DepartmentModel;
import com.br.user_service.model.UserModel;
import com.br.user_service.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.br.user_service.exception.enumeration.ErrorApiCode.*;
import static com.br.user_service.helper.MessageHelper.getMessage;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final DepartmentService departmentService;
    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Override
    @Transactional
    public UserDTO createUser(UserCreateDTO dto) {
        DepartmentModel departmentModel = departmentService.getDepartment(dto.getDepartmentId());
        checkUserWithEmailExists(dto.getEmail());
        UserDTO userDTO = mapper.map(userRepository.save(mapper.map(dto, departmentModel)));
        log.info("User created successfully with id: {}", userDTO.getId());
        return userDTO;
    }

    @Override
    @Transactional
    public UserDTO updateUser(Long id, UserUpdateDTO dto) {
        UserModel userModel = getUser(id);

        if (!dto.getEmail().equals(userModel.getEmail())) {
            checkUserWithEmailExists(dto.getEmail());
        }

        DepartmentModel departmentModel = departmentService.getDepartment(dto.getDepartmentId());
        userModel = mapper.map(userModel, dto, departmentModel);
        UserDTO userDTO = mapper.map(userRepository.save(userModel));
        log.info("User updated successfully with id: {}", userDTO.getId());
        return userDTO;
    }

    @Override
    @Transactional
    public UserDTO deleteUser(Long id) {
        UserModel userModel = getUser(id);
        userModel.setDeleted(true);
        UserDTO userDTO = mapper.map(userRepository.save(userModel));
        log.info("User deleted successfully with id: {}", userDTO.getId());
        return userDTO;
    }

    @Override
    public UserDTO findUserById(Long id) {
        UserModel userModel = getUser(id);
        return mapper.map(userModel);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findByDeletedFalse().stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean checkUserWithEmailExists(String email) {
        Optional<UserModel> userModelOptional = userRepository.findByEmail(email);
        if (userModelOptional.isPresent()){
            log.error(getMessage(USER_EXIST_WITH_EMAIL, email));
            throw new BusinessServiceException(CONFLICT, getMessage(USER_EXIST_WITH_EMAIL, email));
        }
        return true;
    }

    @Override
    public UserModel getUser(Long id) {
        Optional<UserModel> userModelOptional = userRepository.findById(id);

        if (userModelOptional.isEmpty()) {
            log.error(getMessage(USER_NOT_FOUND, id.toString()));
            throw new BusinessServiceException(NOT_FOUND, getMessage(USER_NOT_FOUND, id.toString()));
        }

        return userModelOptional.get();
    }
}
