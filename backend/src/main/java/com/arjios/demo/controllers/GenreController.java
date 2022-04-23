package com.arjios.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arjios.demo.dto.GenreDTO;
import com.arjios.demo.services.GenreService;

@RestController
@RequestMapping(value="/genres")
public class GenreController {
	
	@Autowired
	private GenreService genreService;
	
	@GetMapping
	public ResponseEntity<Page<GenreDTO>> findAll(Pageable pageable) {
		Page<GenreDTO> page = genreService.findAll(pageable);
		return ResponseEntity.ok(page);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<GenreDTO> findById(@PathVariable Long id) {
		GenreDTO genreDTO = genreService.findById(id);
		return ResponseEntity.ok().body(genreDTO);
	}

}
