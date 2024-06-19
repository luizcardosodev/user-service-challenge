package com.br.user_service.interfaces;

import com.br.user_service.dto.UserCreateDTO;
import com.br.user_service.dto.UserDTO;
import com.br.user_service.dto.UserUpdateDTO;
import com.br.user_service.model.UserModel;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserCreateDTO dto);
    UserDTO deleteUser(Long id);
    UserDTO updateUser(Long id, UserUpdateDTO dto);
    UserDTO findUserById(Long id);
    List<UserDTO> getAllUsers();
    Boolean checkUserWithEmailExists(String email);
    UserModel getUser(Long id);
}
