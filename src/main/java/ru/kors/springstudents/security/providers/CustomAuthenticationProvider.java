package ru.kors.springstudents.security.providers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import ru.kors.springstudents.security.authentication.CastomAuthentication;


@Component
public class CustomAuthenticationProvider implements  AuthenticationProvider {

	@Value("${MyProgect.very.secret.key}")
	private String key;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		CastomAuthentication castomAuthentication = (CastomAuthentication)authentication;
		String headerKey = castomAuthentication.getKey();
		
		if (headerKey.equals(key)) {
			return new CastomAuthentication(true, null);
		}
		
		throw new BadCredentialsException("Oh, no!");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return CastomAuthentication.class.equals(authentication);
	}

}
