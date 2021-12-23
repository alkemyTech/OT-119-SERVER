package com.alkemy.ong.controller;

import com.alkemy.ong.service.abstraction.IDeleteFileAmazonService;
import com.alkemy.ong.service.abstraction.IUploadFileAmazonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class BucketController {

  @Autowired
  private IDeleteFileAmazonService deleteFileAmazonService;
  @Autowired
  private IUploadFileAmazonService uploadFileAmazonService;

  @PostMapping("/uploadFile")
  public String uploadFile(@RequestPart(value = "file") MultipartFile file) {
    return this.uploadFileAmazonService.uploadFile(file);
  }

  @DeleteMapping("/deleteFile")
  public String deleteFile(@RequestPart(value = "url") String fileUrl) {
    return this.deleteFileAmazonService.deleteFile(fileUrl);
  }

}
