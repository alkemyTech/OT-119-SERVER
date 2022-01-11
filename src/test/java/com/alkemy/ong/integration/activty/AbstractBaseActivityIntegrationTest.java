package com.alkemy.ong.integration.activty;

import com.alkemy.ong.common.AbstractBaseIntegrationTest;
import com.alkemy.ong.model.entity.Activity;
import com.alkemy.ong.model.request.ActivityDetailsRequest;
import com.alkemy.ong.repository.IActivityRepository;
import java.sql.Timestamp;
import java.time.Instant;
import org.springframework.boot.test.mock.mockito.MockBean;

public abstract class AbstractBaseActivityIntegrationTest extends AbstractBaseIntegrationTest {

  protected static final long ACTIVITY_ID = 1L;

  @MockBean
  protected IActivityRepository activityRepository;

  protected Activity stubActivity() {
    return new Activity(ACTIVITY_ID,
        "activity name",
        "activity content",
        "https://foo.jpg",
        Timestamp.from(Instant.now()),
        false);
  }

  protected ActivityDetailsRequest getActivityDetailsRequest() {
    ActivityDetailsRequest activityDetailsRequest = new ActivityDetailsRequest();
    activityDetailsRequest.setName("activity name");
    activityDetailsRequest.setContent("activity content");
    activityDetailsRequest.setImage("https://foo.jpg");
    return activityDetailsRequest;
  }

}
