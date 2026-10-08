package com.uc.ms_security.repository;

import com.uc.ms_security.entity.UserRole;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

  boolean existsByUserIdAndRoleId(Long userId, Long roleId);  //Duplicados no para asignar permisos

  @EntityGraph(attributePaths = { "user", "role" })
  List<UserRole> findAllByUserId(Long userId);

  @EntityGraph(attributePaths = { "user", "role" })
  List<UserRole> findAllByRoleId(Long roleId);

  @Override
  @EntityGraph(attributePaths = { "user", "role" })
  List<UserRole> findAll();

  @EntityGraph(attributePaths = { "user", "role" })
  Optional<UserRole> findByUserIdAndRoleId(Long userId, Long roleId);

  boolean existsByRoleId(Long roleId);
}
