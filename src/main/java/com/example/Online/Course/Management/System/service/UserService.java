package com.example.Online.Course.Management.System.service;

import java.util.List;

import com.example.Online.Course.Management.System.dto.LoginRequest;
import com.example.Online.Course.Management.System.dto.LoginResponse;
import com.example.Online.Course.Management.System.exception.DuplicateResourceException;
import com.example.Online.Course.Management.System.exception.ResourceNotFoundException;
import com.example.Online.Course.Management.System.jwtToken.JwtService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import com.example.Online.Course.Management.System.dto.UserRequestDto;
import com.example.Online.Course.Management.System.dto.UserResponseDto;
import com.example.Online.Course.Management.System.entity.User;
import com.example.Online.Course.Management.System.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestBody;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ModelMapper modelMapper;
   @Autowired
    private PasswordEncoder passwordEncoder;
   @Autowired
   private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    //Add List Of Users
    public List<UserResponseDto> addAllUsers(List<UserRequestDto> userRequestDtoList){
        // UserRequestDto -> Users
       List<User> users =  userRequestDtoList.stream()
                .map(dto ->{
                    User user = modelMapper.map(dto,User.class);
                    checkEmail(dto.getEmail());
                    user.setPassword(passwordEncoder.encode(dto.getPassword()));
                    return user;
                })
                .toList();
       // Save Users to DB
       List<User> savedUsers = userRepo.saveAll(users);
       // Convert Users -> UserResponseDto and Return it
        return savedUsers.stream()
                .map(user -> modelMapper.map(user, UserResponseDto.class))
                .toList();
    }

    //check email if already exist
    public void checkEmail(String email){
        User user = userRepo.findByEmail(email);
        if(user != null)
            throw new DuplicateResourceException("This Email is already exist " + email);
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
        if(user == null)
            throw new ResourceNotFoundException("Invalid Email id");
        return modelMapper.map(user,UserResponseDto.class);
    }

    //findByRole
    public List<UserResponseDto> getUserByRole(String role){
        List<User> users = userRepo.findByRole(role);
        return users.stream()
                .map(user -> modelMapper.map(user,UserResponseDto.class))
                .toList();
    }

    public ResponseEntity<LoginResponse> loginUser(LoginRequest loginRequest){
        Authentication authentication =  authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));

        if(authentication.isAuthenticated()){
            User user = userRepo.findByEmail(loginRequest.getEmail());
            String token = jwtService.generateToken(user);
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setUserId(user.getUserId());
            loginResponse.setEmail(user.getEmail());
            loginResponse.setUsername(user.getName());
            loginResponse.setRole(user.getRole());
            loginResponse.setToken(token);
            return ResponseEntity.ok(loginResponse);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    
}
