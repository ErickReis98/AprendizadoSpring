package com.dosreis.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	SecurityFilter securityFilter;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf(csrf -> csrf.disable())
			.cors().and()
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests(authorize -> authorize
			.requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
			.requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
			.requestMatchers(HttpMethod.POST, "/auth/registerPerfil").permitAll()
			.requestMatchers(HttpMethod.GET, "/auth").permitAll()
			.requestMatchers(HttpMethod.POST, "/cliente").hasRole("ADMIN")
			.requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
			.requestMatchers(new AntPathRequestMatcher("/swagger-ui/**")).permitAll()
			.anyRequest().authenticated()
			)
			.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
			

		http.headers().frameOptions().disable();

		return http.build();
	} 

    @Bean
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }	
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception{
    	return auth.getAuthenticationManager();
    }
    
    /*
     @Bean
    public UserDetailsService userDetailsService() {
    UserDetails user =
         User.builder()
            .username("Usuario")
            .password(passwordEncoder().encode("user123"))
            .roles("ADMIN")
            .build();
    
// -----> Autenticação em memória
   
    	return new InMemoryUserDetailsManager(user);
    }*/
	
}