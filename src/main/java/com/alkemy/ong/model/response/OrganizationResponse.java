package com.alkemy.ong.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("organization")
public class OrganizationResponse {

  private String image;
  private String address;
  private Integer phone;
  private String email;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private List<SlideResponse> slides;

}
