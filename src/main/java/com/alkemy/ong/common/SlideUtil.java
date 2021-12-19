package com.alkemy.ong.common;

import com.alkemy.ong.model.entity.Slide;
import com.alkemy.ong.model.response.SlideResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SlideUtil {

  public static List<SlideResponse> SlideEntityList2ResponseList(Collection<Slide> entities) {
    List<SlideResponse> dtos = new ArrayList<>();
    SlideResponse response;
    for (Slide entity : entities) {
      response = new SlideResponse();
      response.setImageUrl(entity.getImageUrl());
      response.setOrder(entity.getOrder());
      dtos.add(response);
    }
    return dtos;

  }

}
