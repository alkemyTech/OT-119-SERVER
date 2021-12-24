package com.alkemy.ong.common.mail;

import com.alkemy.ong.common.mail.template.Email;
import com.alkemy.ong.common.mail.template.EmailAddress;
import com.alkemy.ong.common.mail.template.EmailContent;
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
    Email emailTest = buildEmail(toAddress, toName, subject, "text/plain", content);
    emailUtils.send(emailTest);
  }

  @Test
  public void sendEmailCustomContentType() throws SendEmailException {
    Email emailTest = buildEmail(toAddress, toName, subject, contentType, content);
    emailUtils.send(emailTest);
  }

  private Email buildEmail(String toAddress, String toName, String subject, String contentType,
      String content) {
    return new Email(subject,
        new EmailAddress(toAddress, toName),
        new EmailContent(content, contentType));
  }

}
