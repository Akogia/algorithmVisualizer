package com.example.visualizer.algorithmVisualizer.bucket;

public enum BucketName {

    PROFILE_IMAGE("myalgorithmbucket");

    private final String bucketName;

    BucketName(String bucketName){
        this.bucketName = bucketName;
    }

    public String getBucketName(){
        return bucketName;
    }
}
