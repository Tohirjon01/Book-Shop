package uz.bookshop.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.bookshop.dto.request.UserRequestDTO;
import uz.bookshop.dto.response.ResponseDTO;
import uz.bookshop.dto.response.UserResponseDTO;
import uz.bookshop.excetpion.RoleException;
import uz.bookshop.excetpion.UserException;
import uz.bookshop.model.Roles;
import uz.bookshop.model.Users;
import uz.bookshop.repository.RoleRepository;
import uz.bookshop.repository.UserRepository;
import uz.bookshop.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        try {
            Users users;
            Roles role = roleRepository.findById(userRequestDTO.getRoles().getId()).orElseThrow(() -> new RoleException("Role not found"));
            users = userRepository.findByUserName(userRequestDTO.getUserName());
            if (users != null) {
                throw new UserException("Username is already exist");
            }
            users = toEntity(userRequestDTO, role);
            users = userRepository.save(users);
            UserResponseDTO userResponseDTO = toDto(users, role);
            log.info("User created: " + userResponseDTO);
            return userResponseDTO;
        } catch (Exception e) {
            throw new UserException( e.getMessage());
        }
    }

    @Override
    public UserResponseDTO updateUser(UserRequestDTO userRequestDTO, Long id) {
        try {
            Users users = userRepository.findById(id).orElseThrow(() -> new UserException("User not found"));
            Roles roles = roleRepository.findById(users.getRoles().getId()).get();
            updateFromDto(userRequestDTO, users);
            users = userRepository.save(users);
            UserResponseDTO userResponseDTO = toDto(users, roles);
            userResponseDTO.setRoleName(roles.getName());
            log.info("User updated: " + userResponseDTO);
            return userResponseDTO;
        } catch (Exception e) {
            throw new UserException( e.getMessage());
        }


    }

    @Override
    public UserResponseDTO getOneUserById(Long id) {

        Users users = userRepository.findById(id).orElseThrow(() -> new UserException("User not found"));
        return toDto(users ,roleRepository.findById(users.getRoles().getId()).orElseThrow(() -> new RoleException("Role not found")));
    }

    @Override
    public ResponseDTO deleteUserById(Long id) {
        try {
         userRepository.deleteById(id);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("User deleted successfully");
            return responseDTO;
        }
        catch (Exception e) {
            throw new UserException(e.getMessage());
        }
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<Users> usersList = userRepository.findAll();

        return toDtos(usersList);
    }


    private UserResponseDTO toDto(Users users, Roles roles) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(users.getId());
        userResponseDTO.setFirstName(users.getFirstName());
        userResponseDTO.setLastName(users.getLastName());
        userResponseDTO.setUserName(users.getUserName());
        userResponseDTO.setPhoneNumber(users.getPhoneNumber());
        userResponseDTO.setRoleName(roles.getName());
        return userResponseDTO;

    }

    private Users toEntity(UserRequestDTO userRequestDTO, Roles roles) {
        Users users = new Users();
        users.setFirstName(userRequestDTO.getFirstName());
        users.setLastName(userRequestDTO.getLastName());
        users.setUserName(userRequestDTO.getUserName());
        users.setPhoneNumber(userRequestDTO.getPhoneNumber());
        users.setRoles(roles);
        return users;


    }

    private void updateFromDto(UserRequestDTO userRequestDTO, Users users) {
        if (userRequestDTO.getFirstName() != null) {
            users.setFirstName(userRequestDTO.getFirstName());
        }
        if (userRequestDTO.getLastName() != null) {
            users.setLastName(userRequestDTO.getLastName());
        }
        if (userRequestDTO.getUserName() != null) {
            users.setUserName(userRequestDTO.getUserName());
        }
        if (userRequestDTO.getPhoneNumber() != null) {
            users.setPhoneNumber(userRequestDTO.getPhoneNumber());
        }
        if (userRequestDTO.getRoles() != null) {
            users.setRoles(roleRepository.findByName(userRequestDTO.getRoles().getName()));
        }
    }

    private List<UserResponseDTO> toDtos(List<Users> usersList){
        List<UserResponseDTO > userResponseDTOS = new ArrayList<>();
        for (int i = 0; i < usersList.size(); i++) {
            UserResponseDTO userResponseDTO = new UserResponseDTO();
            userResponseDTO.setId(usersList.get(i).getId());
            userResponseDTO.setFirstName(usersList.get(i).getFirstName());
            userResponseDTO.setLastName(usersList.get(i).getLastName());
            userResponseDTO.setUserName(usersList.get(i).getUserName());
            userResponseDTO.setPhoneNumber(usersList.get(i).getPhoneNumber());
            userResponseDTO.setRoleName(usersList.get(i).getRoles().getName());
            userResponseDTOS.add(userResponseDTO);
        }
        return userResponseDTOS;
    }
}
