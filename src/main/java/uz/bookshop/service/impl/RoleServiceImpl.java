package uz.bookshop.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.bookshop.dto.request.RoleRequestDTO;
import uz.bookshop.dto.response.RoleResponseDTO;
import uz.bookshop.excetpion.RoleException;
import uz.bookshop.model.Roles;
import uz.bookshop.repository.RoleRepository;
import uz.bookshop.service.RoleService;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleResponseDTO createRole(RoleRequestDTO roleRequestDTO) {
        try {
            Roles roles = roleRepository.findByName(roleRequestDTO.getName());
            if (roles != null) {
                throw new RoleException("This role already exists");
            }
            roles = toEntity(roleRequestDTO);
            roles = roleRepository.save(roles);
            RoleResponseDTO roleResponseDTO = toDto(roles);
            log.info("Role created");
            return roleResponseDTO;

        } catch (Exception e) {
            throw new RoleException("Role could not be created: " + e.getMessage());
        }
    }

    @Override
    public RoleResponseDTO updateRole(RoleRequestDTO roleRequestDTO, Long id) {
        try {
            Roles roles = roleRepository.findById(id).orElseThrow(() -> new RoleException("This role does not exist"));

            if (roleRequestDTO.getName() != null) {
                roles.setName(roleRequestDTO.getName());
            }
            roles = roleRepository.save(roles);
            RoleResponseDTO roleResponseDTO = toDto(roles);
            roleResponseDTO.setName(roles.getName());
            log.info("Role updated");
            return roleResponseDTO;

        } catch (Exception e) {
            throw new RoleException("Role could not be updated: " + e.getMessage());
        }
    }

    @Override
    public List<RoleResponseDTO> getAllRole() {
        List<Roles> rolesList = roleRepository.findAll();
        List<RoleResponseDTO> roleResponseDTOList = toDtos(rolesList);
        return roleResponseDTOList;


    }

    @Override
    public RoleResponseDTO deleteRole(Long id) {
        roleRepository.deleteById(id);
        RoleResponseDTO roleResponseDTO = new RoleResponseDTO();
        return roleResponseDTO;
    }

    private RoleResponseDTO toDto(Roles roles) {
        RoleResponseDTO roleResponseDTO = new RoleResponseDTO();
        roleResponseDTO.setId(roles.getId());
        roleResponseDTO.setName(roles.getName());
        return roleResponseDTO;
    }

    private Roles toEntity(RoleRequestDTO roleRequestDTO) {
        Roles roles = new Roles();
        roles.setName(roleRequestDTO.getName());
        return roles;
    }

    private List<RoleResponseDTO> toDtos(List<Roles> rolesList) {
        List<RoleResponseDTO> roleResponseDTOS = new ArrayList<>();
        for (int i = 0; i < rolesList.size(); i++) {
            RoleResponseDTO roleResponseDTO = new RoleResponseDTO();
            roleResponseDTO.setName((rolesList.get(i).getName()));
            roleResponseDTO.setName(roleResponseDTO.getName());
            roleResponseDTO.setId(rolesList.get(i).getId());
            roleResponseDTOS.add(roleResponseDTO);

        }
        return roleResponseDTOS;

    }

}
