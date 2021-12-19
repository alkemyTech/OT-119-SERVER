package com.alkemy.ong.common;

import com.alkemy.ong.model.notifications.EmailNotification;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import java.io.IOException;

public class EmailUtils {

  public static int sendEmail(EmailNotification emailNotification) throws IOException {
    Email from = new Email(emailNotification.getFrom());
    String subject = emailNotification.getSubject();
    Email to = new Email(emailNotification.getTo());
    Content content = new Content(emailNotification.getContentType(),
        emailNotification.getContent());
    Mail mail = new Mail(from, subject, to, content);

    SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
    Request request = new Request();
    try {
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(mail.build());
      Response response = sg.api(request);
      System.out.println(response.getStatusCode());
      System.out.println(response.getBody());
      System.out.println(response.getHeaders());
      return response.getStatusCode();
    } catch (IOException ex) {
      throw ex;
    }
  }

}
