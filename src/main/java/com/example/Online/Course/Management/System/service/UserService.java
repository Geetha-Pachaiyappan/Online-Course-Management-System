package com.example.Online.Course.Management.System.service;

import java.util.Date;
import java.util.List;

import com.example.Online.Course.Management.System.enums.Roles;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import com.example.Online.Course.Management.System.dto.UserRequestDto;
import com.example.Online.Course.Management.System.dto.UserResponseDto;
import com.example.Online.Course.Management.System.entity.User;
import com.example.Online.Course.Management.System.repository.UserRepository;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ModelMapper modelMapper;

    //Add List Of Users
    public List<UserResponseDto> addAllUsers(List<UserRequestDto> userRequestDtoList){
        // UserRequestDto -> Users
       List<User> users =  userRequestDtoList.stream()
                .map(dto -> modelMapper.map(dto,User.class))
                .toList();
       // Save Users to DB
       List<User> savedUsers = userRepo.saveAll(users);
       // Convert Users -> UserResponseDto and Return it
        return savedUsers.stream()
                .map(user -> modelMapper.map(user, UserResponseDto.class))
                .toList();
    }

    //Select List Of Users
    public Page<UserResponseDto> getAllUsers(int page, int size){
         //Creating Pageable Object
         Pageable pageable = PageRequest.of(page,size);
         //Get Page of Users
         Page<User> usersPage = userRepo.findAll(pageable);
         //Convert Page of Users -> Page of UserResponseDto and return it
        return usersPage.map(user -> modelMapper.map(user, UserResponseDto.class));
    }

    //findByEmail
    public UserResponseDto getUserByEmail(String email){
        User user = userRepo.findByEmail(email);
        return modelMapper.map(user,UserResponseDto.class);
    }

    //findByRole
    public List<UserResponseDto> getUserByRole(String role){
        List<User> users = userRepo.findByRole(role);
        return users.stream()
                .map(user -> modelMapper.map(user,UserResponseDto.class))
                .toList();
    }
    
}
