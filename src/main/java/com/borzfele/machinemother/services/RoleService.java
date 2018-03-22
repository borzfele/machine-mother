package com.borzfele.machinemother.services;

import com.borzfele.machinemother.models.Role;
import com.borzfele.machinemother.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    public Role findByName(String roleName) {
        return roleRepository.findByName(roleName);
    }

}
