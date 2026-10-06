package com.uc.ms_security.dto.profile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseProfileDTO {

  @NotBlank(message = "El teléfono es obligatorio")
  @Size(max = 20, message = "El teléfono no puede superar 20 caracteres")
  private String phone;

  @NotNull(message = "La fecha de nacimiento es obligatoria")
  private LocalDate birthdate;
}
