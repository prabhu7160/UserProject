package com.vois.user.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.vois.user.entities.CustomUserDetails;
import com.vois.user.entities.UsersProject;
import com.vois.user.repos.UserRepo;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UsersProject user= userRepo.getUserByUserName(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("User not found!");
		}
		
		CustomUserDetails customerUserDetail = new CustomUserDetails(user);
		return customerUserDetail;
	}

}
