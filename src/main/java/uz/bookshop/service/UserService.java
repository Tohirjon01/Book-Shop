package uz.bookshop.service;

import uz.bookshop.dto.request.UserRequestDTO;
import uz.bookshop.dto.response.ResponseDTO;
import uz.bookshop.dto.response.UserResponseDTO;

import java.util.List;

public interface UserService {

    UserResponseDTO createUser(UserRequestDTO userRequestDTO);
    UserResponseDTO updateUser(UserRequestDTO userRequestDTO , Long id);
    UserResponseDTO getOneUserById(Long id);
    ResponseDTO deleteUserById(Long id);
    List<UserResponseDTO> getAllUsers();

}
