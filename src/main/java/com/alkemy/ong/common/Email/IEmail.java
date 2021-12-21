package com.alkemy.ong.common.Email;

public interface IEmail {

  String getTo();

  String getSubject();

  IEmailContent getContent();

}
