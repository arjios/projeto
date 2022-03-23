package com.arjios.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arjios.demo.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}
