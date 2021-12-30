package com.example.visualizer.algorithmVisualizer.profile;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class UserProfile {

    private UUID userProfileID;
    private String username;
    private String profileUserImageLink;    //s3 key

    public UserProfile(UUID userProfileID,
                       String username,
                       String profileUserImageLink) {
        this.userProfileID = userProfileID;
        this.username = username;
        this.profileUserImageLink = profileUserImageLink;
    }

    public UUID getUserProfileID() {
        return userProfileID;
    }

    public void setUserProfileID(UUID userProfileID) {
        this.userProfileID = userProfileID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Optional<String> getProfileUserImageLink() {
        return Optional.ofNullable(profileUserImageLink);
    }

    public void setProfileUserImageLink(String profileUserImageLink) {
        this.profileUserImageLink = profileUserImageLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return Objects.equals(userProfileID, that.userProfileID) &&
                Objects.equals(username, that.username) &&
                Objects.equals(profileUserImageLink, that.profileUserImageLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userProfileID, username, profileUserImageLink);
    }
}
