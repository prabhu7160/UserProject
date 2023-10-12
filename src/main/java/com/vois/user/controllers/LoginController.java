package com.vois.user.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vois.user.entities.JwtRequest;
import com.vois.user.entities.JwtResponse;
import com.vois.user.security.JwtHelper;

@RestController
@RequestMapping("/auth")
public class LoginController {

	//to get the user details
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private JwtHelper helper;
	
	private Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request){
		this.doAuthenticate(request.getUser(), request.getPassword());
		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUser());
		String token = this.helper.generateToken(userDetails);
		
		JwtResponse response = JwtResponse.builder()
				.jwtToken(token)
				.username(userDetails.getUsername())
				.build();
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	private void doAuthenticate(String user, String password) {
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, password);
		try {
			manager.authenticate(authentication);
		}
		catch(BadCredentialsException e) {
			throw new BadCredentialsException("Invalid username or password!!");
		}
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public String exceptionHandler() {
		return "Credentials invalid";
	}
}
