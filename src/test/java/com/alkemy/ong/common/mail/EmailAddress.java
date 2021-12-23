package com.alkemy.ong.common.mail;

public class EmailAddress implements IEmailAddress {

  private String address;
  private String name;

  @Override
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Override
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}