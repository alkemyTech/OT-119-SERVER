package com.alkemy.ong.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SlideResponse {

  private String imageUrl;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String text;
  private int order;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private OrganizationResponse organization;
}
