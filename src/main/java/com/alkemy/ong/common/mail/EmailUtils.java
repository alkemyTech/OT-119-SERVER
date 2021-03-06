package com.alkemy.ong.common.mail;

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
import org.springframework.stereotype.Service;

@Service
public class EmailUtils {

  private static final Logger LOGGER = LoggerFactory.getLogger(EmailUtils.class);

  @Value("${email.sender.sendgrid.token}")
  private String token;

  @Value("${email.sender.from.address}")
  private String fromAddress;

  @Value("${email.sender.from.name}")
  private String fromName;

  public void send(IEmail email) throws SendEmailException {
    Email from = new Email(fromAddress, fromName);
    String subject = email.getSubject();
    Email to = new Email(email.getTo().getAddress(), email.getTo().getName());
    IEmailContent emailContent = email.getContent();
    Content content = new Content(emailContent.getContentType(), emailContent.getValue());
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
        throw new SendEmailException("Fails to send email: " + response.getBody());
      }
    } catch (IOException ex) {
      throw new SendEmailException(ex.getMessage());
    }
  }

}
