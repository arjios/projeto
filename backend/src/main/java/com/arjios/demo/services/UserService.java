package com.arjios.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjios.demo.dto.UserDTO;
import com.arjios.demo.entities.User;
import com.arjios.demo.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional(readOnly = true)
	public Page<UserDTO> findAll(Pageable pageable) {
		Page<User> page = userRepository.findAll(pageable);
		return page.map(x -> new UserDTO(x));
	}
	
	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		Optional<User> object = userRepository.findById(id);
		User entity = object.get();
//		User user = object.orElseThrow(() -> new ResourceNotFoundException("Usuário não existe: " + id));
		return new UserDTO(entity);
	}
}
