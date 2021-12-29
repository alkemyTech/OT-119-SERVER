package com.alkemy.ong.model.response;

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
public class ActivityDetailsResponse {

  private String name;
  private String content;
  private String image;
  private Timestamp timestamp;

}
