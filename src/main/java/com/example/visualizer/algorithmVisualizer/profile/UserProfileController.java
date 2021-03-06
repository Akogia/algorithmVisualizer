package com.example.visualizer.algorithmVisualizer.profile;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/user-profile")
@CrossOrigin("http://localhost:3000/")
public class UserProfileController {

    private final UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping
    public List<UserProfile> getUserProfiles(){
        return userProfileService.getUserProfiles();
    }

    @PostMapping(
            path = "{userProfilID}/image/upload",
            consumes  = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void uploadUserProfileImage(@PathVariable("userProfilID")UUID userProfilID,
                                       @RequestParam("file")MultipartFile file){
        userProfileService.uploadUserProfileImage(userProfilID, file);
    }

    @GetMapping("{userProfilID}/image/download")
    public byte[] downloadProfileImage(@PathVariable("userProfilID")UUID userProfilID){
        return userProfileService.downloadUserProfileImage(userProfilID);
    }
}
