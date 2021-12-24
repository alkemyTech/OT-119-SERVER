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
    EmailTest emailTest = getEmail(toAddress, toName, subject, "text/plain", content);
    emailUtils.send(emailTest);
  }

  @Test
  public void sendEmailCustomContentType() throws SendEmailException {
    EmailTest emailTest = getEmail(toAddress, toName, subject, contentType, content);
    emailUtils.send(emailTest);
  }

  private EmailTest getEmail(String toAddress, String toName, String subject, String contentType,
      String content) {

    EmailAddressTest toEmailAddressTest = new EmailAddressTest();
    toEmailAddressTest.setAddress(toAddress);
    toEmailAddressTest.setName(toName);

    EmailTest emailTest = new EmailTest();
    emailTest.setTo(toEmailAddressTest);
    emailTest.setSubject(subject);

    EmailContentTest emailContentTest = new EmailContentTest();
    emailContentTest.setContentType(contentType);
    emailContentTest.setValue(content);

    emailTest.setContent(emailContentTest);
    return emailTest;
  }

}
