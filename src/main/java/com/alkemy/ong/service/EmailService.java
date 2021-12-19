package com.alkemy.ong.service;

import com.alkemy.ong.common.EmailUtils;
import com.alkemy.ong.model.notifications.EmailNotification;
import java.io.IOException;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  String contentType = "text/plain";

  public int sendEmail(String from, String subject, String to, String content,
      String contentType) throws IOException {
    this.contentType = contentType;
    return sendEmail(from, subject, to, content);
  }

  public int sendEmail(String from, String subject, String to, String content) throws IOException {
    EmailNotification emailNotification = new EmailNotification(from, subject, to, content,
        contentType);
    return EmailUtils.sendEmail(emailNotification);
  }
}
