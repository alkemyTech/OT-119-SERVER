package com.alkemy.ong.common.mail.template;

import com.alkemy.ong.common.mail.IEmail;
import com.alkemy.ong.common.mail.IEmailAddress;
import com.alkemy.ong.common.mail.IEmailContent;

public class Email implements IEmail {

  private final String subject;
  private final IEmailAddress to;
  private final IEmailContent content;

  public Email(String subject, IEmailAddress to, IEmailContent content) {
    this.subject = subject;
    this.to = to;
    this.content = content;
  }

  @Override
  public String getSubject() {
    return subject;
  }

  @Override
  public IEmailAddress getTo() {
    return to;
  }

  @Override
  public IEmailContent getContent() {
    return content;
  }

}
