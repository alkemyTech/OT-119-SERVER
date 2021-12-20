package com.alkemy.ong.common;

import com.alkemy.ong.model.entity.Organization;
import com.alkemy.ong.model.entity.Slide;
import com.alkemy.ong.model.response.OrganizationResponse;
import com.alkemy.ong.model.response.SlideResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.BeanUtils;

public class EntityUtils {

  private EntityUtils() {
  }

  public static OrganizationResponse convertTo(Organization organization) {
    OrganizationResponse organizationResponse = new OrganizationResponse();
    BeanUtils.copyProperties(organization, organizationResponse);
    return organizationResponse;
  }

  public static List<SlideResponse> convertTo(Collection<Slide> slides) {
    List<SlideResponse> slideResponses = new ArrayList<>();
    SlideResponse slideResponse;
    for (Slide slide : slides) {
      slideResponse = new SlideResponse();
      slideResponse.setImageUrl(slide.getImageUrl());
      slideResponse.setOrder(slide.getOrder());
      slideResponses.add(slideResponse);
    }
    return slideResponses;
  }

}
