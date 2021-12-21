package com.alkemy.ong.common.mail;

public class Email implements IEmail {

  private String subject;
  private String to;
  private IEmailContent Content;

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
