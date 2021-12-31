package com.example.visualizer.algorithmVisualizer.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.RandomAccessFile;

@Configuration
public class AmazonConfig {

    private static String accessKey;
    private static String secretKey;



    @Bean
    public AmazonS3 s3() throws Exception{
        try{
        RandomAccessFile raf= new RandomAccessFile("accesskey.txt","r");
        RandomAccessFile raf1 = new RandomAccessFile("secretkey.txt", "r");
        accessKey = raf.readLine();
        secretKey = raf1.readLine();

        System.out.println(accessKey + " and " + secretKey);
        }catch (Exception e){
        System.out.println(e);
        }

        AWSCredentials awsCredentials = new BasicAWSCredentials(
                accessKey,
                secretKey);
        return AmazonS3ClientBuilder
                .standard()
                .withRegion("eu-central-1")
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}
