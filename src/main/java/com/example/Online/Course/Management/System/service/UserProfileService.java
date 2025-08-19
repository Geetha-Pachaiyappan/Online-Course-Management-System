package com.example.Online.Course.Management.System.service;

import com.example.Online.Course.Management.System.dto.UserProfileRequestDto;
import com.example.Online.Course.Management.System.dto.UserProfileResponseDto;
import com.example.Online.Course.Management.System.dto.UserRequestDto;
import com.example.Online.Course.Management.System.dto.UserResponseDto;
import com.example.Online.Course.Management.System.entity.User;
import com.example.Online.Course.Management.System.entity.UserProfile;
import com.example.Online.Course.Management.System.exception.ResourceNotFoundException;
import com.example.Online.Course.Management.System.repository.UserProfileRepository;
import com.example.Online.Course.Management.System.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService {
    @Autowired
    private UserProfileRepository profileRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepo;

    public UserProfileResponseDto addUserProfiles(int userId, long phoneNumber, String address, MultipartFile file) throws IOException {

        //Check if the User Exists
        Optional<User> user = userRepo.findById(userId);
        if(user.isPresent()){
            //Set UserProfile Details
            UserProfile profiles = new UserProfile();
            profiles.setPhoneNumber(phoneNumber);
            profiles.setAddress(address);
            profiles.setProfilePicture(file.getBytes());

            //Save User Profile Details to UserProfile Table
            UserProfile savedUserProfiles = profileRepo.save(profiles);
            //Save UserProfile Info to User Table
            user.get().setUserProfile(savedUserProfiles);
          //  user.get().setUpdatedDate(new Date());
            userRepo.save(user.get());
            //Convert Saved UserProfile to User Profile Response Dto And Return it
            return modelMapper.map(savedUserProfiles, UserProfileResponseDto.class);
        }
      return null;
    }

    public byte[] getProfilePicture(int profileId){
        UserProfile userProfile = profileRepo.findById(profileId)
                .orElseThrow(()->new ResourceNotFoundException("Profile id not found "+ profileId));
        return userProfile.getProfilePicture();
    }

    public UserProfileResponseDto getProfileInfo(int profileId){
        UserProfile userProfile = profileRepo.findById(profileId)
                .orElseThrow(()->new ResourceNotFoundException("Profile id not found "+ profileId));
        return modelMapper.map(userProfile, UserProfileResponseDto.class);
    }


}
