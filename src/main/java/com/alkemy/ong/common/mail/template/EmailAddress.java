package com.alkemy.ong.common.mail.template;

import com.alkemy.ong.common.mail.IEmailAddress;

public final class EmailAddress implements IEmailAddress {

  private final String address;
  private final String name;

  public EmailAddress(String address, String name) {
    this.address = address;
    this.name = name;
  }

  @Override
  public String getAddress() {
    return address;
  }

  @Override
  public String getName() {
    return name;
  }

}