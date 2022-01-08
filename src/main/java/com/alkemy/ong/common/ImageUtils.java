package com.alkemy.ong.common;

import com.alkemy.ong.config.AmazonClient;
import com.alkemy.ong.exception.ThirdPartyException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.InputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageUtils {

  private static final Logger LOGGER = LoggerFactory.getLogger(ImageUtils.class);

  @Autowired
  private AmazonClient amazonClient;
  @Autowired
  private AmazonS3 amazonS3;

  public String upload(InputStream inputStream, String fileName, String contentType)
      throws ThirdPartyException {
    try {
      ObjectMetadata metadata = new ObjectMetadata();
      metadata.setContentType(contentType);
      PutObjectRequest request = new PutObjectRequest(amazonClient.getBucketName(), fileName,
          inputStream, metadata);
      request.setCannedAcl(CannedAccessControlList.PublicRead);
      amazonS3.putObject(request);
    } catch (
        SdkClientException e) {
      LOGGER.error(e.getMessage());
      throw new ThirdPartyException(
          "There was an internal error with the Amazon S3 service. Please contact the support or try again later.");
    }
    return amazonS3.getUrl(amazonClient.getBucketName(), fileName).toExternalForm();
  }
}
