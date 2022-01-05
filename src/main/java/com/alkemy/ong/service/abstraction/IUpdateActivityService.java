package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.request.ActivityDetailsRequest;
import com.alkemy.ong.model.response.ActivityDetailsResponse;

public interface IUpdateActivityService {

  ActivityDetailsResponse update(Long id, ActivityDetailsRequest activityDetailsRequest);

}
