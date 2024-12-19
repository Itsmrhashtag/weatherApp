package com.syvora.weatherApp.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Service
public class S3Service {

    private final S3Client s3Client;
    private final String region;

    @Value("${aws.s3.bucket.name}")
    private String bucketName;
    
    public S3Service(@Value("${aws.access-key}") String accessKey,
                     @Value("${aws.secret-key}") String secretKey,
                     @Value("${aws.s3.region}") String region) {
        this.s3Client = S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey)))
                .build();
        this.region = region;
    }

    public void createBucket(String bucketName) {
        CreateBucketRequest bucketRequest = CreateBucketRequest.builder()
                .bucket(bucketName)
                .build();
        
        s3Client.createBucket(bucketRequest);
        System.out.println("Bucket created: " + bucketName);
    }

    public void uploadToS3(String key, String content) {
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        s3Client.putObject(request, software.amazon.awssdk.core.sync.RequestBody.fromString(content, StandardCharsets.UTF_8));
        System.out.println("Data uploaded to S3: " + key);
    }
    
    public boolean doesFileExist(String key) {
        try {
            // Check the metadata of the object (headObject does not download the file, just gets metadata)
            s3Client.headObject(builder -> builder.bucket(bucketName).key(key));
            return true; // File exists if no exception is thrown
        } catch (Exception e) {
            // If an exception is thrown, it means the file does not exist
            return false;
        }
    }

    
    
    public JsonNode fetchJsonFromS3(String key) {
        try {
        	System.out.println(bucketName+"=======================================");
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(this.bucketName)
                    .key(key)
                    .build();

            InputStream inputStream = s3Client.getObject(getObjectRequest);
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readTree(inputStream); 
        } catch (Exception e) {
            throw new RuntimeException("Error fetching or parsing JSON data", e);
        }
    }
}
