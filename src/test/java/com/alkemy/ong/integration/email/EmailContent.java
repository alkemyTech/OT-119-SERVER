package com.alkemy.ong.integration.email;

import com.alkemy.ong.model.notifications.IEmailContent;

public class EmailContent implements IEmailContent {

  private String value;
  private String contentType;

  @Override
  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }
}
