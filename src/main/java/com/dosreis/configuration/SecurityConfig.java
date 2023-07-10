package com.dosreis.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf(csrf -> csrf.disable())
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests(authorize -> authorize
			/*.requestMatchers(HttpMethod.POST, "/usuario").permitAll()
			.requestMatchers(HttpMethod.GET, "/usuario").permitAll()
			.requestMatchers(HttpMethod.GET, "/cliente/**").permitAll()*/
			.requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
			.requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
			.requestMatchers(HttpMethod.GET, "/auth").permitAll()
			.requestMatchers(HttpMethod.POST, "/cliente").hasRole("ADMIN")
			.requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
			.anyRequest().authenticated());
			
			

		http.headers().frameOptions().disable();

		return http.build();
	} 

   /* @Bean
    public UserDetailsService userDetailsService() {
    UserDetails user =
         User.builder()
            .username("Sarah")
            .password(passwordEncoder().encode("sarah123"))
            .roles("ADMIN")
            .build();
    
// -----> Autenticação em memória
   
    	return new InMemoryUserDetailsManager(user);
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }	
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception{
    	return auth.getAuthenticationManager();
    }
	
}

