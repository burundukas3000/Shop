package com.project.backend.services;

import com.project.backend.models.Role;
import com.project.backend.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepo;
    public Role getRoleByName(String roleName) {
        return roleRepo.findRoleByRoleName(roleName);
    }
}
