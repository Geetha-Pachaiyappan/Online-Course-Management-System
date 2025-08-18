package com.example.Online.Course.Management.System.controller;

import com.example.Online.Course.Management.System.dto.UserRequestDto;
import com.example.Online.Course.Management.System.dto.UserResponseDto;
import com.example.Online.Course.Management.System.entity.User;
import com.example.Online.Course.Management.System.enums.Roles;
import com.example.Online.Course.Management.System.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<List<UserResponseDto>> addUsersList(@Valid @RequestBody List<UserRequestDto> users){
        return new ResponseEntity<>(userService.addAllUsers(users),HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<UserResponseDto>> getAllUsers(@RequestParam(defaultValue = "0" ) int page,
                                                             @RequestParam(defaultValue = "10") int size){
        return new ResponseEntity<>(userService.getAllUsers(page,size),HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserResponseDto> getByEmail(@PathVariable("email") String email){
        return ResponseEntity.ok().body(userService.getUserByEmail(email));
    }

    @GetMapping("/user-role")
    public ResponseEntity<List<UserResponseDto>> getByRole(@RequestParam("role") String role){
        return ResponseEntity.ok().body(userService.getUserByRole(role));
    }

}
