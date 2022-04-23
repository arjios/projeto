package com.arjios.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjios.demo.dto.GenreDTO;
import com.arjios.demo.entities.Genre;
import com.arjios.demo.repositories.GenreRepository;
import com.arjios.demo.services.exceptions.ResourceNotFoundException;

@Service
public class GenreService {
	
	@Autowired
	private GenreRepository genreRepository;
	
	@Transactional(readOnly = true)
	public Page<GenreDTO> findAll(Pageable pageable) {
		Page<Genre> page = genreRepository.findAll(pageable);
		return page.map(x -> new GenreDTO(x));
	}
	
	@Transactional(readOnly = true)
	public GenreDTO findById(Long id) {
		Optional<Genre> object = genreRepository.findById(id);
		Genre entity = object.orElseThrow(() -> new ResourceNotFoundException("Genero inexistente: " + id));
		return new GenreDTO(entity);
	}

}
