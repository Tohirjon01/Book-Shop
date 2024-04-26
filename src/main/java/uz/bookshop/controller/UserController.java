package uz.bookshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.bookshop.dto.request.UserRequestDTO;
import uz.bookshop.dto.response.UserResponseDTO;
import uz.bookshop.repository.UserRepository;
import uz.bookshop.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/user")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity.ok(userService.createUser(userRequestDTO));
    }
    @PutMapping("/user/update")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UserRequestDTO userRequestDTO,
                                                      @RequestParam Long userId) {
        return ResponseEntity.ok(userService.updateUser(userRequestDTO, userId));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponseDTO> getOneUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getOneUserById(id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteUserById(id));
    }

    @GetMapping("/user/getAll")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
