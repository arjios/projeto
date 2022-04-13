package com.arjios.demo.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjios.demo.dto.RoleDTO;
import com.arjios.demo.dto.UserDTO;
import com.arjios.demo.dto.UserInsertDTO;
import com.arjios.demo.entities.Role;
import com.arjios.demo.entities.User;
import com.arjios.demo.repositories.RoleRepository;
import com.arjios.demo.repositories.UserRepository;
import com.arjios.demo.services.exceptions.ResourceNotFoundException;




@Service
public class UserService implements UserDetailsService {
	
	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthService authService;
	
	
	@Transactional(readOnly = true)
	public Page<UserDTO> findAll(Pageable pageable) {
		Page<User> page = userRepository.findAll(pageable);
		return page.map(x -> new UserDTO(x));
	}
	
	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		authService.validateSelfOrAdmin(id);
		
		Optional<User> object = userRepository.findById(id);
		User entity = object.orElseThrow(() -> new ResourceNotFoundException("Usuário não existe: " + id));
		return new UserDTO(entity);
	}

	@Transactional
	public UserDTO insert(UserInsertDTO dto) {
		User user = new User();
		copyUserToDTO(user, dto);
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		user = userRepository.save(user);
		return new UserDTO(user);
	}

	@Transactional
	public UserDTO update(Long id, UserDTO dto) {
		Optional<User> object = userRepository.findById(id);
		User user = object.orElseThrow(() -> new ResourceNotFoundException("Usuário não existe: " + id));
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user = userRepository.save(user);
		return new UserDTO(user);
	}
	
	@Transactional
	public void delete(Long id) {
		Optional<User> object = userRepository.findById(id);
		User user = object.orElseThrow(() -> new ResourceNotFoundException("Usuário não existe: " + id));
		userRepository.deleteById(user.getId());
	}
	
	private void copyUserToDTO(User user, UserInsertDTO dto) {
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.getRoles().clear();
		for(RoleDTO roleDTO : dto.getRoles()) {
			Role role = roleRepository.getOne(roleDTO.getId());
			user.getRoles().add(role);
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if(user == null) {
			logger.error("User not found: " + username);
			throw new UsernameNotFoundException("Email não encontrado");
		}
		logger.info("User found: " + username);
		return user;
	}
}
