package com.uc.ms_security.service;

import com.uc.ms_security.dto.role.CreateRoleDTO;
import com.uc.ms_security.dto.role.RoleResponseDTO;
import com.uc.ms_security.dto.role.UpdateRoleDTO;
import com.uc.ms_security.entity.Role;
import com.uc.ms_security.exception.ApplicationException;
import com.uc.ms_security.exception.ErrorCase;
import com.uc.ms_security.mapper.RoleMapper;
import com.uc.ms_security.repository.RoleRepository;
import com.uc.ms_security.repository.UserRoleRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

  private final RoleRepository roleRepository;
  private final UserRoleRepository userRoleRepository;
  private final RoleMapper roleMapper;

  public RoleResponseDTO create(CreateRoleDTO dto) {
    if (roleRepository.existsByNameIgnoreCase(dto.getName())) {
      throw new ApplicationException(
        ErrorCase.ALREADY_EXISTS,
        "Ya existe un rol con ese nombre"
      );
    }

    Role role = roleMapper.toEntity(dto);
    return roleMapper.toResponseDTO(roleRepository.save(role));
  }

  public List<RoleResponseDTO> findAll() {
    return roleMapper.toResponseDTOList(roleRepository.findAll());
  }

  public RoleResponseDTO findById(Long id) {
    return roleMapper.toResponseDTO(findRole(id));
  }

  public RoleResponseDTO update(Long id, UpdateRoleDTO dto) {
    Role role = findRole(id);

    if (roleRepository.existsByNameIgnoreCaseAndIdNot(dto.getName(), id)) {
      throw new ApplicationException(
        ErrorCase.ALREADY_EXISTS,
        "Ya existe un rol con ese nombre"
      );
    }

    roleMapper.updateEntity(dto, role);
    return roleMapper.toResponseDTO(roleRepository.save(role));
  }

  public void delete(Long id) {
    Role role = findRole(id);

    if (userRoleRepository.existsByRoleId(id)) {
      throw new ApplicationException(
        ErrorCase.INVALID_OPERATION,
        "No se puede eliminar un rol que está asignado"
      );
    }

    roleRepository.delete(role);
  }

  private Role findRole(Long id) {
    return roleRepository
      .findById(id)
      .orElseThrow(
        () ->
          new ApplicationException(ErrorCase.NOT_FOUND, "Rol no encontrado")
      );
  }
}
