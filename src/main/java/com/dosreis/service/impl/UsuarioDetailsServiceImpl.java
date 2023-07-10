package com.dosreis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dosreis.domain.repository.UsuarioRepository;

@Service
//@Transactional
public class UsuarioDetailsServiceImpl implements UserDetailsService {

	@Autowired
    private UsuarioRepository userRepository;

   

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       /* Usuario userModel = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        return new User(userModel.getUsername(), userModel.getPassword(), true, true, true,true, userModel.getAuthorities());*/
    	
    	
    	return userRepository.findByLogin(username);
    }
}