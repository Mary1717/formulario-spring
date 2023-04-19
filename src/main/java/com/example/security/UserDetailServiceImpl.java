/**
 * 
 */
package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.models.User;
import com.example.repositories.UserRepository;


@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
		User userOne = userRepository.
				findOneByUser(user).
				orElseThrow(() -> new UsernameNotFoundException("El usuario con usuario " + user + " no existe."));
		
		return new UserDetailsImpl(userOne);
	}
	
	
	
}
