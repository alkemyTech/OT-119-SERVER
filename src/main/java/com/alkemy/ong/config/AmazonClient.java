package com.alkemy.ong.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class AmazonClient {

  @Value("${amazonProperties.endpointUrl}")
  private String endpointUrl;
  @Value("${amazonProperties.bucketName}")
  private String bucketName;
  @Value("${amazonProperties.accessKey}")
  private String accessKey;
  @Value("${amazonProperties.secretKey}")
  private String secretKey;
  @Value("${amazonProperties.region}")
  private String region;

  @Bean
  private AmazonS3 initializeAmazon() {
    AWSCredentials awsCredentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
    return AmazonS3ClientBuilder
        .standard()
        .withRegion(this.region)
        .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
        .build();
  }

  public String getBucketName() {
    return this.bucketName;
  }

}
