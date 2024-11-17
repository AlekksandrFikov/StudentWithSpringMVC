package ru.kors.springstudents.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.AllArgsConstructor;
import ru.kors.springstudents.security.filters.CastomAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityConfiguration {
	
	private final CastomAuthenticationFilter castomAuthenticationFilter;
	
	@SuppressWarnings("deprecation")
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
				
		return httpSecurity
				.addFilterAt(castomAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests().anyRequest().authenticated().and()
				.build();
	}

	
	@Bean
	public PasswordEncoder passwordEncoder() { 
		return NoOpPasswordEncoder.getInstance();
	}
	
	

//	private final  UserRepository userRepository;
//	
//	@Bean
//	public UserDetailsService jpaUserDetailsService() {
//		return new JpaUserDetailsService(userRepository);
//	}
	
	//@Bean
//	public UserDetailsService userDetailsService() {
//		
//		var user = User.withUsername("fikov")
//			.password("12345")
//			.authorities("read")
//			.build();
//		
//		var udm = new InMemoryUserDetailsManager();
//		udm.createUser(user);
//
//		return udm;
//	}
	
	
}
