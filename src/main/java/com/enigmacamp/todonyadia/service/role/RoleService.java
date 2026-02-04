package com.enigmacamp.todonyadia.service.role;

import com.enigmacamp.todonyadia.entities.Role;

import java.util.Optional;
import java.util.UUID;

public interface RoleService {
    Optional<Role> findByName(String name);
}
