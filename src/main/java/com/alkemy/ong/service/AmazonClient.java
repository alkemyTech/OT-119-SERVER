package com.alkemy.ong.service;

import com.alkemy.ong.service.abstraction.IDeleteFileAmazonService;
import com.alkemy.ong.service.abstraction.IUploadFileAmazonService;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AmazonClient implements IDeleteFileAmazonService, IUploadFileAmazonService {

  private AmazonS3 s3client;

  @Value("${amazonProperties.endpointUrl}")
  private String endpointUrl;
  @Value("${amazonProperties.bucketName}")
  private String bucketName;
  @Value("${amazonProperties.accessKey}")
  private String accessKey;
  @Value("${amazonProperties.secretKey}")
  private String secretKey;

  @PostConstruct
  private void initializeAmazon() {
    AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
    this.s3client = new AmazonS3Client(credentials);
  }

  private File convertMultiPartToFile(MultipartFile file) throws IOException {
    File convFile = new File(file.getOriginalFilename());
    FileOutputStream fileOutputStream = new FileOutputStream(convFile);
    fileOutputStream.write(file.getBytes());
    fileOutputStream.close();
    return convFile;
  }

  private String generateFileName(MultipartFile multiPart) {
    return new Date().getTime() + "-" +
        multiPart.getOriginalFilename().replace(" ", "_");
  }

  private void uploadFileTos3bucket(String fileName, File file) {
    s3client.putObject(new PutObjectRequest(bucketName, fileName, file)
        .withCannedAcl(CannedAccessControlList.PublicRead));
  }

  @Override
  public String uploadFile(MultipartFile multipartFile) {

    String fileUrl = "";
    try {
      File file = convertMultiPartToFile(multipartFile);
      String fileName = generateFileName(multipartFile);
      fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;
      uploadFileTos3bucket(fileName, file);
      file.delete();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return fileUrl;
  }

  @Override
  public String deleteFile(String fileUrl) {
    String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
    s3client.deleteObject(new DeleteObjectRequest(bucketName + "/", fileName));
    return "Successfully deleted";
  }

}
