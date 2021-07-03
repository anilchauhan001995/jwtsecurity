package com.security.jwtsecurity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.security.jwtsecurity.entity.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long>{

}
