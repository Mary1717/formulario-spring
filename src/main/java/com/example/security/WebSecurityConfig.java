/**
 * 
 */
package com.example.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig {
	
	private final UserDetailsService userDetailsService;
	private final JWTAuthorizationFilter jwtAuthorizationFilter;
	
	public WebSecurityConfig(UserDetailsService userDetailsService, JWTAuthorizationFilter jwtAuthorizationFilter) {
		super();
		this.userDetailsService = userDetailsService;
		this.jwtAuthorizationFilter = jwtAuthorizationFilter;
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
		JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
		jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
		jwtAuthenticationFilter.setFilterProcessesUrl("/login");
		
		return http
				.cors().and()
				.csrf().disable()
				.authorizeRequests()
				.antMatchers("/api/user")
				.permitAll()
				.anyRequest()
				.authenticated()
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.addFilter(jwtAuthenticationFilter)
				.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	
	@Bean
	AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder())
				.and()
				.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfiguration=new CorsConfiguration();
		corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "X-Auth-Token"));
		corsConfiguration.addExposedHeader("*");
		UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration);
		return source;
	}
}
