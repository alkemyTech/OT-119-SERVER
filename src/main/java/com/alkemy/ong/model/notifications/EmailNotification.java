package com.alkemy.ong.model.notifications;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailNotification {

  private String from;
  private String subject;
  private String to;
  private String content;
  private String contentType;

}
