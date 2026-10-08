package com.uc.ms_security.dto.user;

import com.uc.ms_security.dto.userrole.UserRoleResponseDTO;
import java.util.List;
import lombok.Value;

@Value
public class UserRolesResponseDTO {

  Long id;
  String name;
  String email;
  List<UserRoleResponseDTO> roles;
}
