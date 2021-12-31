package com.example.visualizer.algorithmVisualizer.profile;

import com.example.visualizer.algorithmVisualizer.bucket.BucketName;
import com.example.visualizer.algorithmVisualizer.filestore.FileStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;


import static org.apache.http.entity.ContentType.*;

@Service
public class UserProfileService {

    private final UserProfileDataAccessService userProfileDataAccessService;
    private final FileStore fileStore;

    @Autowired
    public UserProfileService(UserProfileDataAccessService userProfileDataAccessService, FileStore fileStore) {
        this.userProfileDataAccessService = userProfileDataAccessService;
        this.fileStore = fileStore;
    }
    List<UserProfile> getUserProfiles(){
        return userProfileDataAccessService.getUserProfiles();
    }

    public void uploadUserProfileImage(UUID userProfilID, MultipartFile file) {
        // 1. Check if image is not empty
        isEmpty(file);

        // 2. if file is an image
        isImage(file);

        // 3. the user exists in our database
        UserProfile userProfile = userExist(userProfilID);

        // 4. Grab some metadata from file if any
        Map<String, String> metadata = extractMetadata(file);

        // 5. Store the image in s3 and update database (userProfileImageLink) with s3 image link
        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), userProfile.getUserProfileID());
        System.out.println("The path is: " + path);
        String filename = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());
        System.out.println("The filename is: " + filename);

        try{
            fileStore.save(path, filename, Optional.of(metadata), file.getInputStream());
            userProfile.setProfileUserImageLink(filename);
        }catch(IOException e){
            throw new IllegalStateException(e);
        }


    }

    private void isImage(MultipartFile file) {
        if(!Arrays.asList(
                IMAGE_JPEG.getMimeType(),
                IMAGE_GIF.getMimeType(),
                IMAGE_PNG.getMimeType())
                .contains(file.getContentType())){
            throw new IllegalStateException("File must be an image [" + file.getContentType() + "]");
        }
        System.out.println(file.getContentType());
    }

    private void isEmpty(MultipartFile file) {
        if(file.isEmpty()){
            System.out.println(file.isEmpty());
            throw new IllegalStateException("Cannot upload empty file [ " + file.getSize() + "]");
        }
    }

    private Map<String, String> extractMetadata(MultipartFile file) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Size", String.valueOf(file.getSize()));
        return metadata;
    }

    private UserProfile userExist(UUID userProfilID){
        for(UserProfile userProfile : getUserProfiles()) {
            if (userProfilID.equals(userProfile.getUserProfileID())) {
                System.out.println("UserProfile exist");
                return userProfile;
            }
        }
        throw new IllegalStateException(String.format("User profile %s not found", userProfilID));
    }

    public byte[] downloadUserProfileImage(UUID userProfilID) {
        UserProfile user =  userExist(userProfilID);
        String path = String.format("%s/%s",
                BucketName.PROFILE_IMAGE.getBucketName(),
                user.getUserProfileID());

        return user.getProfileUserImageLink().map(key -> fileStore.download(path, key)).orElse(new byte[0]);
    }
}
