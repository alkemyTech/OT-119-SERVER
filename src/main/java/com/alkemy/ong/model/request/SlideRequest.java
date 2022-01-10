package com.alkemy.ong.model.request;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SlideRequest {

  @NotBlank(message = "The base64-encoded image is required.")
  private String encodedImage;
  private String text;
  private int order;
  @NotBlank(message = "A contentType is required.")
  private String contentType;
  private String fileName;
}
