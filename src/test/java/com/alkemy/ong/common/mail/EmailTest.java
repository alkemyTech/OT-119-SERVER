package com.alkemy.ong.common.mail;

public class EmailTest implements IEmail {

  private String subject;
  private IEmailAddress to;
  private IEmailContent Content;

  @Override
  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  @Override
  public IEmailAddress getTo() {
    return to;
  }

  public void setTo(IEmailAddress to) {
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
