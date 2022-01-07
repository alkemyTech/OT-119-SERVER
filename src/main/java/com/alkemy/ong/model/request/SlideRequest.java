package com.alkemy.ong.model.request;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SlideRequest {

  @NotBlank
  private String encodedImage;
  private String text;
  private int order;
  @NotBlank
  private String contentType;
  private String fileName;
}
