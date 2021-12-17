package com.project.backend.services;

import com.project.backend.models.Role;

public interface RoleService {

    Role getRoleByName(String roleName);
}
