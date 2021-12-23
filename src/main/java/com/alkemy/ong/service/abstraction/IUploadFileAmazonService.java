package com.alkemy.ong.service.abstraction;

import org.springframework.web.multipart.MultipartFile;

public interface IUploadFileAmazonService {

  String uploadFile(MultipartFile multipartFile);

}
