package com.alkemy.ong.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDetailsRequest {

  @NotBlank
  @Pattern(regexp = "([A-Z a-z]+[^0-9])", message = "category`s name must be string")
  private String name;
  private String description;
  private String image;

}
