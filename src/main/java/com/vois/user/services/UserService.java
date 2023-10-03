package com.vois.user.services;

import java.util.List;

import com.vois.user.dtos.UsersDto;

public interface UserService {
	UsersDto registerNewUser(UsersDto user);
	UsersDto createUser(UsersDto userDto);
	UsersDto updateUser(UsersDto userDto,Integer userId);
	UsersDto getUserById(Integer userId);
	List<UsersDto> getAllUsers();
	void deleteUserById(Integer userId);
	
}