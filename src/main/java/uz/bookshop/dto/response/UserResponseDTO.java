package uz.bookshop.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class UserResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String phoneNumber;
    private String roleName;
}
