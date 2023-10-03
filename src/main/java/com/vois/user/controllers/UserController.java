package com.vois.user.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vois.user.dtos.ApiResponse;
import com.vois.user.dtos.UsersDto;
import com.vois.user.services.UserService;

import jakarta.validation.Valid;

//Endpoint for user

@RestController
@RequestMapping("/userproject")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/add")
	public ResponseEntity<UsersDto> createNewUser(
			@Valid @RequestBody UsersDto userDto)
	{
		UsersDto addUser = this.userService.createUser(userDto);
		return new ResponseEntity<>(addUser,HttpStatus.CREATED);
		
	}
//PUT
	@PutMapping("/edit/{userId}")
	public ResponseEntity<UsersDto> updateUser(@RequestBody UsersDto userDto, @PathVariable Integer userId){
		UsersDto updatedUser = this.userService.updateUser(userDto, userId);
		return ResponseEntity.ok(updatedUser);
	}

//DELETE
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
		this.userService.deleteUserById(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Area deleted successfully",true),HttpStatus.OK);
	}
//GET
	@GetMapping("/display/all")
	public ResponseEntity<List<UsersDto>> displayAllUsers() {
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	@GetMapping("/display/{userId}")
	public ResponseEntity<UsersDto> getUserById(@PathVariable Integer userId){
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}
}
