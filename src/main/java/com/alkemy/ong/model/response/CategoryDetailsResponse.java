package com.alkemy.ong.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDetailsResponse {

  private String name;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String description;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String image;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Timestamp timestamp;

}
