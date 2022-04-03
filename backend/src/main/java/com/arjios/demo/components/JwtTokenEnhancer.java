package com.arjios.demo.components;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.arjios.demo.entities.User;
import com.arjios.demo.repositories.UserRepository;

@Component
public class JwtTokenEnhancer implements TokenEnhancer {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {


		User user = userRepository.findByEmail(authentication.getName());
		Map<String, Object> map = new HashMap<>();
		map.put("id", user.getId());
		map.put("name", user.getName());
		map.put("email", user.getEmail());
		
		DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
		
		token.setAdditionalInformation(map);
		
		return accessToken;
	}

}
