package com.alkemy.ong.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SlideDetailsResponse {

  private String imageUrl;
  private String text;
  private int order;
  private OrganizationResponse organization;
}

