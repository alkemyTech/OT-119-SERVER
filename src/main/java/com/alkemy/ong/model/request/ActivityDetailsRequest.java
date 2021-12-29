package com.alkemy.ong.model.request;

import java.sql.Timestamp;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDetailsRequest {

  @NotBlank(message = "A name is required in order to create this activity.")
  private String name;
  @NotBlank(message = "Content must be specified for this activity.")
  private String content;
  @NotBlank(message = "Image cannot be empty.")
  private String image;
}
