package com.alkemy.ong.common;

import com.alkemy.ong.common.mail.EmailUtils;
import com.alkemy.ong.config.AmazonClient;
import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.InputStream;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageUtils {

  private static final Logger LOGGER = LoggerFactory.getLogger(EmailUtils.class);

  @Autowired
  private AmazonClient amazonClient;
  @Autowired
  private AmazonS3 amazonS3;

  public String upload(InputStream inputStream, String fileName, String contentType) {
    try {
      ObjectMetadata metadata = new ObjectMetadata();
      metadata.setContentType(contentType);
      PutObjectRequest request = new PutObjectRequest(amazonClient.getBucketName(), fileName,
          inputStream, metadata);
      request.setCannedAcl(CannedAccessControlList.PublicRead);
      amazonS3.putObject(request);
    } catch (
        AmazonClientException e) {
      LOGGER.error(e.getMessage());
    }
    URL objectUrl = amazonS3.getUrl(amazonClient.getBucketName(), fileName);
    return objectUrl.toExternalForm();
  }
}
