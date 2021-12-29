package com.alkemy.ong.service;

import com.alkemy.ong.common.DtoUtils;
import com.alkemy.ong.common.EntityUtils;
import com.alkemy.ong.model.entity.Activity;
import com.alkemy.ong.model.request.ActivityDetailsRequest;
import com.alkemy.ong.model.response.ActivityDetailsResponse;
import com.alkemy.ong.repository.IActivityRepository;
import com.alkemy.ong.service.abstraction.ICreateActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl implements ICreateActivityService {

  @Autowired
  IActivityRepository activityRepository;

  @Override
  public ActivityDetailsResponse create(ActivityDetailsRequest activityRequest) {
    Activity activity = DtoUtils.convertTo(activityRequest);
    return EntityUtils.convertTo(activityRepository.save(activity));
  }
}
