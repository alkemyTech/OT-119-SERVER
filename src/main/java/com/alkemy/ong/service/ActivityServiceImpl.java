package com.alkemy.ong.service;

import com.alkemy.ong.common.DtoUtils;
import com.alkemy.ong.common.EntityUtils;
import com.alkemy.ong.model.entity.Activity;
import com.alkemy.ong.model.request.ActivityDetailsRequest;
import com.alkemy.ong.model.response.ActivityDetailsResponse;
import com.alkemy.ong.repository.IActivityRepository;
import com.alkemy.ong.service.abstraction.ICreateActivityService;
import com.alkemy.ong.service.abstraction.IUpdateActivityService;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl implements ICreateActivityService, IUpdateActivityService {

  private static final String ACTIVITY_NOT_FOUND_MESSAGE = "Activity not found.";

  @Autowired
  private IActivityRepository activityRepository;

  @Override
  public ActivityDetailsResponse create(ActivityDetailsRequest activityRequest) {
    Activity activity = DtoUtils.convertTo(activityRequest);
    return EntityUtils.convertTo(activityRepository.save(activity));
  }

  @Override
  public ActivityDetailsResponse update(Long id, ActivityDetailsRequest activityDetailsRequest) {
    Activity activity = getActivity(id);
    activity.setName(activityDetailsRequest.getName());
    activity.setImage(activityDetailsRequest.getImage());
    activity.setContent(activityDetailsRequest.getContent());
    return EntityUtils.convertTo(activityRepository.save(activity));
  }

  private Activity getActivity(Long id) {
    Optional<Activity> activityOptional = activityRepository.findById(id);
    if (activityOptional.isEmpty() || activityOptional.get().isSoftDelete()) {
      throw new EntityNotFoundException(ACTIVITY_NOT_FOUND_MESSAGE);
    }
    return activityOptional.get();
  }

}
