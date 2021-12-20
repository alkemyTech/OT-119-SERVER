package com.alkemy.ong.integration.email;

import com.alkemy.ong.model.notifications.IEmail;
import com.alkemy.ong.model.notifications.IEmailContent;

public class Email implements IEmail {

  private String from;
  private String subject;
  private String to;
  private IEmailContent Content;

  @Override
  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  @Override
  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  @Override
  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  @Override
  public IEmailContent getContent() {
    return Content;
  }

  public void setContent(IEmailContent content) {
    Content = content;
  }
}
