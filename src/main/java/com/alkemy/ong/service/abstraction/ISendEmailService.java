package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.common.mail.template.EmailAddress;
import com.alkemy.ong.exception.SendEmailException;

public interface ISendEmailService {

  void sendEmail(EmailAddress toEmailAddress) throws SendEmailException;
}
