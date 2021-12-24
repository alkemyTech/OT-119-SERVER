package com.alkemy.ong.common.mail;

public class EmailContentTest implements IEmailContent {

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
