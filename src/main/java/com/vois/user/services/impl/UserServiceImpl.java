package com.vois.user.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vois.user.dtos.UsersDto;
import com.vois.user.entities.UsersProject;
import com.vois.user.repos.UserRepo;
import com.vois.user.services.UserService;
import com.vois.user.exceptions.ResourceNotFoundException;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper modelMapper;

//	@Override
//    public UsersDto getUserById(Integer userId) {
//        UsersProject user = this.userRepo.findById(userId)
//                .orElseThrow(() -> new ResourceNotFoundException("Users", "userId", userId));
//        return modelMapper.map(user, UsersDto.class);
//    }
	@Override
	public UsersDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		UsersProject user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("Users","userId",userId));
		return this.userToDto(user);
	}

	@Override
	public List<UsersDto> getAllUsers() {
		// TODO Auto-generated method stub
		List<UsersProject> users = this.userRepo.findAll();
		List<UsersDto> usersDto = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return usersDto;
	}

	@Override
	public void deleteUserById(Integer userId) {
		UsersProject user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("Users","userId",userId));
		this.userRepo.delete(user);
	}
	public UsersProject dtoToUser(UsersDto userDto)
	{
		UsersProject user = this.modelMapper.map(userDto, UsersProject.class);
		
		return user;
	}
	public UsersDto userToDto(UsersProject user)
	{
		UsersDto userDto = this.modelMapper.map(user, UsersDto.class);
		
		return userDto;
	}

	@Override
	public UsersDto registerNewUser(UsersDto userDto) {
		UsersProject user = this.dtoToUser(userDto); 
		UsersProject savedUser=this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UsersDto createUser(UsersDto userDto) {
		UsersProject user = this.dtoToUser(userDto);
		UsersProject savedUser = this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UsersDto updateUser(UsersDto userDto, Integer userId) {
		//Users updatedUser = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","userId",userId));
		UsersProject updatedUser = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","userId",userId));
		updatedUser.setUserName(userDto.getUserName());
		updatedUser.setMobile(userDto.getMobile());
		updatedUser.setPassword(userDto.getPassword());
		updatedUser.setEmail(userDto.getEmail());
		
		//id,name,mobile,password,email
		UsersProject updateSavedUser = this.userRepo.save(updatedUser);
		UsersDto updatedUserDto = this.userToDto(updateSavedUser);
		
		return updatedUserDto;
	}
}