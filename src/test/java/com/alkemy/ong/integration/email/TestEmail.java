package com.alkemy.ong.integration.email;

import com.alkemy.ong.common.EmailUtils;
import com.alkemy.ong.exception.SendEmailException;
import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TestPropertySource("/application.properties")
public class TestEmail {

  @Value("${email.apikey}")
  private String apiKey;

  @Value("${email.from}")
  private String from;

  @Value("${email.to}")
  private String to;

  @Value("${email.subject}")
  private String subject;

  @Value("${email.content}")
  private String content;

  @Value("${email.contentType}")
  private String contentType;


  @Test
  public void sendEmailDefaultContentType() throws SendEmailException, IOException {
    Email email = new Email();
    email.setFrom(from);
    email.setTo(to);
    email.setSubject(subject);

    EmailContent emailContent = new EmailContent();
    emailContent.setContentType("text/plain");
    emailContent.setValue(content);

    email.setContent(emailContent);
    EmailUtils.send(email, apiKey);
  }

  @Test
  public void sendEmailCustomContentType() throws SendEmailException, IOException {
    Email email = new Email();
    email.setFrom(from);
    email.setTo(to);
    email.setSubject(subject);

    EmailContent emailContent = new EmailContent();
    emailContent.setContentType(contentType);
    emailContent.setValue(content);

    email.setContent(emailContent);
    EmailUtils.send(email, apiKey);
  }
}
