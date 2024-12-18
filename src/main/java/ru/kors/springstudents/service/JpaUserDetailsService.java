package ru.kors.springstudents.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ru.kors.springstudents.repository.UserRepository;
import ru.kors.springstudents.security.SecurityUser;

@Service
@AllArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		
		var user = userRepository.findByUsername(username);
		
		return user.map(SecurityUser::new)
				.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
	}

}
