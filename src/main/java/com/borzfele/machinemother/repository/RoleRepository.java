package com.borzfele.machinemother.repository;

import com.borzfele.machinemother.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String roleName);

}