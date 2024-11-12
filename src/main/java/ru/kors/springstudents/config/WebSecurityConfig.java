package ru.kors.springstudents.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig {

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
