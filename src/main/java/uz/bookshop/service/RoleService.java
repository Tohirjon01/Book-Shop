package uz.bookshop.service;

import uz.bookshop.dto.request.RoleRequestDTO;
import uz.bookshop.dto.response.RoleResponseDTO;

import java.util.List;

public interface RoleService {
    RoleResponseDTO createRole(RoleRequestDTO roleRequestDTO);
    RoleResponseDTO updateRole(RoleRequestDTO roleRequestDTO , Long id);
    List<RoleResponseDTO> getAllRole();
    RoleResponseDTO deleteRole(Long id);

}
