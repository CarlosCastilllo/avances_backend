package com.uc.ms_security.mapper;

import com.uc.ms_security.dto.role.CreateRoleDTO;
import com.uc.ms_security.dto.role.RoleResponseDTO;
import com.uc.ms_security.dto.role.UpdateRoleDTO;
import com.uc.ms_security.entity.Role;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

  public Role toEntity(CreateRoleDTO dto) {
    Role role = new Role();
    role.setName(dto.getName());
    role.setDescription(dto.getDescription());
    return role;
  }

  public void updateEntity(UpdateRoleDTO dto, Role role) {
    role.setName(dto.getName());
    role.setDescription(dto.getDescription());
  }

  public RoleResponseDTO toResponseDTO(Role role) {
    return new RoleResponseDTO(
      role.getId(),
      role.getName(),
      role.getDescription()
    );
  }

  public List<RoleResponseDTO> toResponseDTOList(List<Role> roles) {
    return roles.stream().map(this::toResponseDTO).toList();
  }
}
