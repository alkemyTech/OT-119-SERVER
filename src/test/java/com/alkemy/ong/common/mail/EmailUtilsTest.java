package com.alkemy.ong.common.mail;

import com.alkemy.ong.exception.SendEmailException;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@Ignore
@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource("/application.properties")
public class EmailUtilsTest {

  @Autowired
  private EmailUtils emailUtils;

  @Value("${email.sender.to.address}")
  private String toAddress;
  @Value("${email.sender.to.name}")
  private String toName;
  @Value("${email.sender.subject}")
  private String subject;
  @Value("${email.sender.content}")
  private String content;
  @Value("${email.sender.contentType}")
  private String contentType;

  @Test
  public void sendEmailDefaultContentType() throws SendEmailException {
    Email email = getEmail(toAddress, toName, subject, "text/plain", content);
    emailUtils.send(email);
  }

  @Test
  public void sendEmailCustomContentType() throws SendEmailException {
    Email email = getEmail(toAddress, toName, subject, contentType, content);
    emailUtils.send(email);
  }

  private Email getEmail(String toAddress, String toName, String subject, String contentType,
      String content) {

    EmailAddress toEmailAddress = new EmailAddress();
    toEmailAddress.setAddress(toAddress);
    toEmailAddress.setName(toName);

    Email email = new Email();
    email.setTo(toEmailAddress);
    email.setSubject(subject);

    EmailContent emailContent = new EmailContent();
    emailContent.setContentType(contentType);
    emailContent.setValue(content);

    email.setContent(emailContent);
    return email;
  }

}
