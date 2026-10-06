package com.uc.ms_security.service;

import com.uc.ms_security.dto.permission.CreatePermissionDTO;
import com.uc.ms_security.dto.permission.PermissionResponseDTO;
import com.uc.ms_security.dto.permission.UpdatePermissionDTO;
import com.uc.ms_security.entity.Permission;
import com.uc.ms_security.mapper.PermissionMapper;
import com.uc.ms_security.repository.PermissionRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class PermissionService {

  private final PermissionRepository permissionRepository;
  private final PermissionMapper permissionMapper;

  public PermissionResponseDTO create(CreatePermissionDTO dto) {
    Permission permission = permissionMapper.toEntity(dto);
    return permissionMapper.toResponseDTO(
      permissionRepository.save(permission)
    );
  }

  public List<PermissionResponseDTO> findAll() {
    return permissionMapper.toResponseDTOList(permissionRepository.findAll());
  }

  public PermissionResponseDTO findById(Long id) {
    return permissionMapper.toResponseDTO(findPermission(id));
  }

  public PermissionResponseDTO update(Long id, UpdatePermissionDTO dto) {
    Permission permission = findPermission(id);
    permissionMapper.updateEntity(dto, permission);
    return permissionMapper.toResponseDTO(
      permissionRepository.save(permission)
    );
  }

  public void delete(Long id) {
    permissionRepository.delete(findPermission(id));
  }

  private Permission findPermission(Long id) {
    return permissionRepository
      .findById(id)
      .orElseThrow(() ->
        new ResponseStatusException(
          HttpStatus.NOT_FOUND,
          "Permiso no encontrado"
        )
      );
  }
}
