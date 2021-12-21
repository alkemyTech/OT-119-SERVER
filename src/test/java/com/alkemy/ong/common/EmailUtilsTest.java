package com.alkemy.ong.common;

import com.alkemy.ong.common.Email.Email;
import com.alkemy.ong.common.Email.EmailContent;
import com.alkemy.ong.exception.SendEmailException;
import java.io.IOException;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource("/application.properties")
public class EmailUtilsTest {

  @Autowired
  EmailUtils emailUtils;

  @Value("${email.sender.to}")
  private String to;
  @Value("${email.sender.subject}")
  private String subject;
  @Value("${email.sender.content}")
  private String content;
  @Value("${email.sender.contentType}")
  private String contentType;

  @Test
  public void sendEmailDefaultContentType() throws SendEmailException, IOException {
    Email email = new Email();
    email.setTo(to);
    email.setSubject(subject);

    EmailContent emailContent = new EmailContent();
    emailContent.setContentType("text/plain");
    emailContent.setValue(content);

    email.setContent(emailContent);
    emailUtils.send(email);
  }

  @Ignore
  @Test
  public void sendEmailCustomContentType() throws SendEmailException, IOException {
    Email email = new Email();
    email.setTo(to);
    email.setSubject(subject);

    EmailContent emailContent = new EmailContent();
    emailContent.setContentType(contentType);
    emailContent.setValue(content);

    email.setContent(emailContent);
    emailUtils.send(email);
  }
}
