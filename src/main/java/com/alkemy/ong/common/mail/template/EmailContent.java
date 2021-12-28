package com.alkemy.ong.common.mail.template;

import com.alkemy.ong.common.mail.IEmailContent;

public final class EmailContent implements IEmailContent {

  private final String value;
  private final String contentType;

  public EmailContent(String value, String contentType) {
    this.value = value;
    this.contentType = contentType;
  }

  @Override
  public String getValue() {
    return value;
  }

  @Override
  public String getContentType() {
    return contentType;
  }

}
