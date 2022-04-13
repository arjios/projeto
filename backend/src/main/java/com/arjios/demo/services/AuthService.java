package com.arjios.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjios.demo.entities.User;
import com.arjios.demo.repositories.UserRepository;
import com.arjios.demo.services.exceptions.ForbiddenException;
import com.arjios.demo.services.exceptions.UnauthorizedException;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;

	@Transactional(readOnly = true)
	public User authenticated() {
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getName();
			return userRepository.findByEmail(userName);
		}
		catch(Exception e) {
			throw new UnauthorizedException("Usuario não encontrado");
		}
	}
	
	public void validateSelfOrAdmin(Long userId) {
		User user = authenticated();
		if(!user.getId().equals(userId) && !user.isRoleAdmin("ROLE_ADMIN")) {
			throw new ForbiddenException("Acesso não permitido!");
		}
	}
}
