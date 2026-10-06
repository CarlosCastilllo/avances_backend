package com.uc.ms_security.dto.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseRoleDTO {

  @NotBlank(message = "El nombre del rol es obligatorio")
  @Size(max = 100, message = "El nombre no puede superar 100 caracteres")
  private String name;

  @NotBlank(message = "La descripción es obligatoria")
  @Size(max = 255, message = "La descripción no puede superar 255 caracteres")
  private String description;
}
