package com.alkemy.ong.test;

import com.alkemy.ong.service.EmailService;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(args = {"--from=", "--subject=",
    "--to=", "--content=", "--contentType="})
public class EmailTest {

  @Autowired
  EmailService emailService;
  @Value("${from}")
  private String from;
  @Value("${subject}")
  private String subject;
  @Value("${to}")
  private String to;
  @Value("${content}")
  private String content;
  @Value("${contentType}")
  private String contentType;

  @Test
  public void sendEmail() throws IOException {
    int response;
    if (contentType.isEmpty()) {
      response = emailService.sendEmail(from, subject, to,
          content);
    } else {
      response = emailService.sendEmail(from, subject, to,
          content, contentType);
    }
    Assert.assertEquals(202, response);
  }
}
