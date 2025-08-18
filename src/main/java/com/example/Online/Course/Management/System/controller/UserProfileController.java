package com.example.Online.Course.Management.System.controller;

import com.example.Online.Course.Management.System.dto.UserProfileResponseDto;
import com.example.Online.Course.Management.System.entity.UserProfile;
import com.example.Online.Course.Management.System.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/profile")
public class UserProfileController {

    @Autowired
    private UserProfileService profileService;

    @PostMapping
    public ResponseEntity<UserProfileResponseDto> addProfiles(@RequestParam("userId") int userId,
                                                              @RequestParam("phoneNumber") long phoneNumber,
                                                              @RequestParam("address") String address,
                                                              @RequestParam("profilePicture") MultipartFile profilePicture) throws IOException {
        return ResponseEntity.ok().body(profileService.addUserProfiles(userId,phoneNumber,address,profilePicture));
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getProfileImage(@PathVariable("id") int profileId){
        byte[] image = profileService.getProfilePicture(profileId);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
                .body(image);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileResponseDto> getProfiles(@PathVariable("id") int profileId){
        return ResponseEntity.ok().body(profileService.getProfileInfo(profileId));
    }
}
