package ru.kors.springstudents.security;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import ru.kors.springstudents.model.Authority;

@AllArgsConstructor
public class SecurityAuthority implements GrantedAuthority {
	
	private final Authority authority;

	@Override
	public String getAuthority() {
		
		return authority.getName();
	}

}
