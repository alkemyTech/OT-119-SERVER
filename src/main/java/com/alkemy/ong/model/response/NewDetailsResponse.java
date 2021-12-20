package com.alkemy.ong.model.response;

import com.fasterxml.jackson.annotation.JsonRootName;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("news")
public class NewDetailsResponse {

  private long id;
  private String name;
  private String content;
  private String image;
  private CategoryDetailsResponse category;
  private Timestamp timestamp;

}
