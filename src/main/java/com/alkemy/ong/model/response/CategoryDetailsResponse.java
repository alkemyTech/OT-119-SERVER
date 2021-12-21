package com.alkemy.ong.model.response;

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
  private String description;
  private String image;
  private Timestamp timestamp;

}
