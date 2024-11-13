package ru.kors.springstudents.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import ru.kors.springstudents.model.User;

@AllArgsConstructor
public class SecurityUser implements UserDetails {

	private static final long serialVersionUID = -2971744706614840062L;
	private final User user; 
	
	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user.getAuthorities()
				.stream().map(SecurityAuthority::new)
				.collect(Collectors.toSet()); 
	}
}
