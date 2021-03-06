package com.arjios.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arjios.demo.dto.RoleDTO;
import com.arjios.demo.services.RoleService;

@RestController
@RequestMapping(value="/roles")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping
	public ResponseEntity<Page<RoleDTO>> findAll(Pageable pageable) {
		Page<RoleDTO> page = roleService.findAll(pageable);
		return ResponseEntity.ok(page);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<RoleDTO> findById(@PathVariable Long id) {
		RoleDTO roleDTO = roleService.findById(id);
		return ResponseEntity.ok().body(roleDTO);
	}

}
