package com.alkemy.ong.integration.activty;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

import com.alkemy.ong.common.AbstractBaseIntegrationTest;
import com.alkemy.ong.config.security.ApplicationRole;
import com.alkemy.ong.model.entity.Activity;
import com.alkemy.ong.model.request.ActivityDetailsRequest;
import com.alkemy.ong.model.response.ActivityDetailsResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CreateActivityTest extends AbstractBaseIntegrationTest {

  private final String PATH = "/activities";

  @Test
  public void shouldReturnForbiddenWhenUserIsNotUserRole() {
    Activity activity = stubActivity();
    when(activityRepository.save(isA(Activity.class))).thenReturn(activity);
    setAuthorizationHeaderBasedOn(ApplicationRole.USER.getFullRoleName());
    ActivityDetailsRequest activityDetailsRequest = getActivityDetailsRequest();

    ResponseEntity<ActivityDetailsResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.POST,
        new HttpEntity<>(activityDetailsRequest,headers),
        ActivityDetailsResponse.class);

    assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
  }

  @Test
  public void shouldCreateActivitySuccessfully() {
    Activity activity = stubActivity();
    when(activityRepository.save(isA(Activity.class))).thenReturn(activity);
    setAuthorizationHeaderBasedOn(ApplicationRole.ADMIN.getFullRoleName());
    ActivityDetailsRequest activityDetailsRequest = getActivityDetailsRequest();

    ResponseEntity<ActivityDetailsResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.POST,
        new HttpEntity<>(activityDetailsRequest,headers),
        ActivityDetailsResponse.class);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
  }

  private ActivityDetailsRequest getActivityDetailsRequest() {
    ActivityDetailsRequest activityDetailsRequest = new ActivityDetailsRequest();
    activityDetailsRequest.setName("Nombre Actividad");
    activityDetailsRequest.setContent("Contenido");
    activityDetailsRequest.setImage("https://foo.jpg");
    return activityDetailsRequest;
  }


}
