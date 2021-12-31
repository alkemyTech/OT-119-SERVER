package com.alkemy.ong.service;

import com.alkemy.ong.common.mail.EmailUtils;
import com.alkemy.ong.common.mail.template.Email;
import com.alkemy.ong.common.mail.template.EmailAddress;
import com.alkemy.ong.common.mail.template.EmailContent;
import com.alkemy.ong.exception.SendEmailException;
import com.alkemy.ong.model.entity.Organization;
import com.alkemy.ong.service.abstraction.IGetOrganizationService;
import com.alkemy.ong.service.abstraction.ISendEmailService;
import java.util.Locale;
import java.util.ResourceBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WelcomeEmailService implements ISendEmailService {

  @Autowired
  private IGetOrganizationService getOrganizationService;

  @Autowired
  private EmailUtils emailUtils;

  public void sendEmail(EmailAddress toEmailAddress) throws SendEmailException {
    Organization organization = getOrganizationService.getOrganization();
    ResourceBundle bundle
        = ResourceBundle.getBundle("messages", Locale.forLanguageTag("es-ES"));

    String subject = bundle.getString("email.organization.welcome.subject");
    String contentType = "text/plain";
    String message = String.format(bundle.getString("email.organization.welcome.message"),
        toEmailAddress.getName(), organization.getName(), organization.getAddress(),
        organization.getPhone(), organization.getEmail());
    EmailContent welcomeEmail = new EmailContent(message, contentType);
    Email email = new Email(subject, toEmailAddress, welcomeEmail);
    emailUtils.send(email);
  }

}
