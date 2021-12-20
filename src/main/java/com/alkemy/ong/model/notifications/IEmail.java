package com.alkemy.ong.model.notifications;

public interface IEmail {

  String getTo();

  String getFrom();

  String getSubject();

  IEmailContent getContent();

}
