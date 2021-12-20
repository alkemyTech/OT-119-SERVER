package com.alkemy.ong.common;

import com.alkemy.ong.exception.SendEmailException;
import com.alkemy.ong.model.notifications.IEmail;
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
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {

  private static final Logger logger = LoggerFactory.getLogger(EmailUtils.class);
  private static String apiKey;

  private EmailUtils() {
  }

  public static void send(IEmail email, String apiKey) throws SendEmailException, IOException {
    EmailUtils.apiKey = apiKey;
    EmailUtils.send(email);
  }

  public static void send(IEmail email)
      throws IOException, SendEmailException {
    Email from = new Email(email.getFrom());
    String subject = email.getSubject();
    Email to = new Email(email.getTo());
    Content content = new Content(email.getContent().getContentType(),
        email.getContent().getValue());
    Mail mail = new Mail(from, subject, to, content);
    SendGrid sendGrid = new SendGrid(apiKey);
    Request request = new Request();
    try {
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(mail.build());
      Response response = sendGrid.api(request);
      logger.info(String.valueOf(response.getStatusCode()));
      logger.info(response.getBody());
      logger.info(String.valueOf(response.getHeaders()));
      if (!(response.getStatusCode() >= 200) || !(response.getStatusCode() < 300)) {
        throw new SendEmailException(
            "Error in SendGrid response, please check your configuration.");
      }
    } catch (IOException ex) {
      throw new SendEmailException(ex.getMessage());
    }
  }

  @Value("${email.apikey}")
  public void setApiKey(String apiKey) {
    EmailUtils.apiKey = apiKey;
  }
}
