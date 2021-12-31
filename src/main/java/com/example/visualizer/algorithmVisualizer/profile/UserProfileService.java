package com.example.visualizer.algorithmVisualizer.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class UserProfileService {

    private final UserProfileDataAccessService userProfileDataAccessService;
    @Autowired
    public UserProfileService(UserProfileDataAccessService userProfileDataAccessService) {
        this.userProfileDataAccessService = userProfileDataAccessService;
    }
    List<UserProfile> getUserProfiles(){
        return userProfileDataAccessService.getUserProfiles();
    }

    public void uploadUserProfileImage(UUID userProfilID, MultipartFile file) {
        // 1. Check if image is not empty
        // 2. if file is an image
        // 3. the user exists in our database
        // 4. Grab some metadata from file if any
        // 5. Store the image in s3 and update database (userProfileImageLink) with s3 image link
    }
}
