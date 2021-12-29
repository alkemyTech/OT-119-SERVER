package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.request.ActivityDetailsRequest;
import com.alkemy.ong.model.response.ActivityDetailsResponse;

public interface ICreateActivityService {

  ActivityDetailsResponse create(ActivityDetailsRequest activityRequest);

}
