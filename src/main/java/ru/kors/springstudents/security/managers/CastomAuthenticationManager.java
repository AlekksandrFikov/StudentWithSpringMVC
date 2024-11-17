package ru.kors.springstudents.security.managers;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import ru.kors.springstudents.security.providers.CustomAuthenticationProvider;

@Component
@AllArgsConstructor
public class CastomAuthenticationManager implements AuthenticationManager{

	private final CustomAuthenticationProvider customAuthenticationProvider;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		if (customAuthenticationProvider.supports(authentication.getClass())) {
			return customAuthenticationProvider.authenticate(authentication);
		}
			return authentication;
	}
}
