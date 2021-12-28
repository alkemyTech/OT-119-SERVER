package com.alkemy.ong.service;

import com.alkemy.ong.common.mail.EmailUtils;
import com.alkemy.ong.common.mail.template.Email;
import com.alkemy.ong.common.mail.template.EmailAddress;
import com.alkemy.ong.common.mail.template.EmailContent;
import com.alkemy.ong.exception.SendEmailException;
import com.alkemy.ong.model.entity.Organization;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.service.abstraction.IGetOrganizationService;
import java.util.Locale;
import java.util.ResourceBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  @Autowired
  IGetOrganizationService organizationService;

  @Autowired
  EmailUtils emailUtils;

  public void sendWelcomeEmail(User user) throws SendEmailException {
    Organization organization = organizationService.getOrganization();
    EmailAddress toEmailAddress = new EmailAddress(user.getEmail(),
        String.format("%s %s", user.getFirstName(), user.getLastName()));

    ResourceBundle bundle
        = ResourceBundle.getBundle("messages", Locale.forLanguageTag("es-ES"));

    String subject = bundle.getString("email.organization.welcome.subject");
    String contentType = "text/plain";
    String message = String.format(bundle.getString("email.organization.welcome.message"),
        user.getFirstName(), user.getLastName(), organization.getName(), organization.getAddress(),
        organization.getPhone(), organization.getEmail());
    EmailContent welcomeEmail = new EmailContent(message, contentType);
    Email email = new Email(subject, toEmailAddress, welcomeEmail);
    emailUtils.send(email);
  }

}
