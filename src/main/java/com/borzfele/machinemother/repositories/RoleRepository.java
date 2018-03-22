package com.borzfele.machinemother.repositories;

import com.borzfele.machinemother.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String roleName);

}