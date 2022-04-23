package com.arjios.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjios.demo.dto.RoleDTO;
import com.arjios.demo.entities.Role;
import com.arjios.demo.repositories.RoleRepository;
import com.arjios.demo.services.exceptions.ResourceNotFoundException;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Transactional(readOnly = true)
	public Page<RoleDTO> findAll(Pageable pageable) {
		Page<Role> page = roleRepository.findAll(pageable);
		return page.map(x -> new RoleDTO(x));
	}
	
	@Transactional(readOnly = true)
	public RoleDTO findById(Long id) {
		Optional<Role> object = roleRepository.findById(id);
		Role entity = object.orElseThrow(() -> new ResourceNotFoundException("Genero inexistente: " + id));
		return new RoleDTO(entity);
	}

}
