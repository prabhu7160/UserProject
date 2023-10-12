package com.vois.user;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.vois.user.dtos.UsersDto;
import com.vois.user.entities.UsersProject;
import com.vois.user.exceptions.ResourceNotFoundException;
import com.vois.user.repos.UserRepo;
import com.vois.user.services.impl.UserServiceImpl;

public class UserProjectApplicationTests {

	@Mock
    private ModelMapper modelMapper;
	
    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
//    @Test
//    public void testGetUserById() {
//        int userId = 1;
//        UsersProject user = new UsersProject();
//        user.setId(userId);
//        when(userRepo.findById(userId)).thenReturn(Optional.of(user));
//
//        when(modelMapper.map(user, UsersDto.class)).thenReturn(new UsersDto()); 
//          
//        UsersDto result = userService.getUserById(userId);
//
//        assertNotNull(result);
//        assertEquals(userId, result.getId());
//        verify(userRepo, times(1)).findById(userId);
//    }


    @Test
    public void testGetAllUsers() {
        UsersProject user1 = new UsersProject();
        user1.setId(1);
        UsersProject user2 = new UsersProject();
        user2.setId(2);
        List<UsersProject> users = Arrays.asList(user1, user2);

        when(userRepo.findAll()).thenReturn(users);

        List<UsersDto> result = userService.getAllUsers();

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(userRepo, times(1)).findAll();
    }

    @Test
    public void testDeleteUserById() {
        int userId = 1;
        UsersProject user = new UsersProject();
        user.setId(userId);
        when(userRepo.findById(userId)).thenReturn(Optional.of(user));

        assertDoesNotThrow(() -> userService.deleteUserById(userId));

        verify(userRepo, times(1)).delete(user);
    }

    @Test
    public void testUpdateUser() {
        int userId = 1;
        UsersDto userDto = new UsersDto();
        userDto.setId(userId);
        UsersProject user = new UsersProject();
        user.setId(userId);

        when(userRepo.findById(userId)).thenReturn(Optional.of(user));
        when(userRepo.save(user)).thenReturn(user);

        when(modelMapper.map(any(), eq(UsersDto.class))).thenReturn(userDto);
        
        UsersDto result = userService.updateUser(userDto, userId);

        assertNotNull(result);
        assertEquals(userId, result.getId());

        verify(userRepo, times(1)).findById(userId);
        verify(userRepo, times(1)).save(user);
    }

    @Test
    public void testUpdateUserThrowsException() {
        int userId = 1;
        UsersDto userDto = new UsersDto();
        userDto.setId(userId);

        when(userRepo.findById(userId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.updateUser(userDto, userId));

        verify(userRepo, times(1)).findById(userId);
        verify(userRepo, never()).save(any());
    }
}
