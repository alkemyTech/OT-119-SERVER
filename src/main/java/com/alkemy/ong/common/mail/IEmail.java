package com.alkemy.ong.common.mail;

public interface IEmail {

  IEmailAddress getTo();

  String getSubject();

  IEmailContent getContent();

}
