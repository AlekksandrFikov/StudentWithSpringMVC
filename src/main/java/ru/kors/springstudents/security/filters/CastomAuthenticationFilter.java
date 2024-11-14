package ru.kors.springstudents.security.filters;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import ru.kors.springstudents.security.authentication.CastomAuthentication;
import ru.kors.springstudents.security.managers.CastomAuthenticationManager;

@Component
@AllArgsConstructor
public class CastomAuthenticationFilter extends OncePerRequestFilter {

	private final CastomAuthenticationManager castomAuthenticationManager;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		var requestKey = String.valueOf(request.getHeader("x-api-key"));
		
		if ("null".equals(requestKey) || requestKey == null) {
			filterChain.doFilter(request, response);
		} 
		
		var castomAuthentication = new CastomAuthentication(false, requestKey);
		
		try {
			var authentication = castomAuthenticationManager.authenticate(castomAuthentication);
		
			if (authentication.isAuthenticated()) {
				SecurityContextHolder.getContext().setAuthentication(authentication);
				filterChain.doFilter(request, response);
			} else {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			}
		} 
		catch (AuthenticationException e) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
		
	}
}
