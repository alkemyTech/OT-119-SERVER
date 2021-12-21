package com.alkemy.ong.common;

import com.alkemy.ong.common.Email.IEmail;
import com.alkemy.ong.exception.SendEmailException;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class EmailUtils {

  private static final Logger LOGGER = LoggerFactory.getLogger(EmailUtils.class);
  @Value("${email.sender.sendgrid.token}")
  private String token;

  @Value("${email.sender.from}")
  private String emailFrom;

  public void send(IEmail email)
      throws SendEmailException {
    Email from = new Email(emailFrom);
    String subject = email.getSubject();
    Email to = new Email(email.getTo());
    Content content = new Content(email.getContent().getContentType(),
        email.getContent().getValue());
    Mail mail = new Mail(from, subject, to, content);
    SendGrid sendGrid = new SendGrid(token);
    Request request = new Request();
    try {
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(mail.build());
      Response response = sendGrid.api(request);
      LOGGER.info("API response code: " + response.getStatusCode());
      LOGGER.debug("API response: " + response.getBody());
      LOGGER.debug("API headers: " + response.getHeaders());
      if ((response.getStatusCode() != 202)) {
        throw new SendEmailException(
            "Error in SendGrid response, please check your configuration.");
      }
    } catch (IOException ex) {
      throw new SendEmailException(ex.getMessage());
    }
  }
}
