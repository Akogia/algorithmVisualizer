package com.example.visualizer.algorithmVisualizer.datastore;

import com.example.visualizer.algorithmVisualizer.profile.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class FakeUserProfileDataStore {
    private static final List<UserProfile> USER_PROFILES = new ArrayList<>();


    static {
        USER_PROFILES.add(new UserProfile(UUID.fromString("677d3103-5d29-4b9f-a776-6ac0f6a60e09"),"MaxMustermann", null));
        USER_PROFILES.add(new UserProfile(UUID.fromString("ec669eb1-e5b5-467d-a131-f2a32c534d86"),"AntonAntonio", null));
    }

    public List<UserProfile> getUserProfiles(){
        return USER_PROFILES;
    }
}
