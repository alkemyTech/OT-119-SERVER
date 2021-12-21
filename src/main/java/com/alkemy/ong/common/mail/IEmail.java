package com.alkemy.ong.common.mail;

public interface IEmail {

  String getTo();

  String getSubject();

  IEmailContent getContent();

}
