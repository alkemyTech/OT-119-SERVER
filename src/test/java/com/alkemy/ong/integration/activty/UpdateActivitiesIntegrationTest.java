package com.alkemy.ong.integration.activty;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.alkemy.ong.config.security.ApplicationRole;
import com.alkemy.ong.model.entity.Activity;
import com.alkemy.ong.model.request.ActivityDetailsRequest;
import com.alkemy.ong.model.response.ErrorResponse;
import java.util.Optional;
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
public class UpdateActivitiesIntegrationTest extends AbstractBaseActivityIntegrationTest {

  private final String PATH = "/activities/" + ACTIVITY_ID;

  @Test
  public void shouldReturnForbiddenWhenUserIsNotUserRole() {
    Activity activity = stubActivity();
    when(activityRepository.findById(eq(ACTIVITY_ID))).thenReturn(Optional.of(activity));
    when(activityRepository.save(eq(activity))).thenReturn(activity);

    ActivityDetailsRequest activityDetailsRequest = getActivityDetailsRequest();
    ResponseEntity<Object> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.PUT,
        new HttpEntity<>(activityDetailsRequest, headers),
        Object.class);

    assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
  }

  @Test
  public void shouldReturnNotFoundWhenIdNoExist() {
    when(activityRepository.findById(eq(ACTIVITY_ID))).thenReturn(Optional.empty());
    setAuthorizationHeaderBasedOn(ApplicationRole.ADMIN.getFullRoleName());

    ActivityDetailsRequest activityDetailsRequest = getActivityDetailsRequest();
    ResponseEntity<ErrorResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.PUT,
        new HttpEntity<>(activityDetailsRequest, headers),
        ErrorResponse.class);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Activity not found.", response.getBody().getMessage());
  }

  @Test
  public void shouldUpdateActivitySuccessfully() {
    Activity activity = stubActivity();
    when(activityRepository.findById(eq(ACTIVITY_ID))).thenReturn(Optional.of(activity));
    when(activityRepository.save(eq(activity))).thenReturn(activity);
    setAuthorizationHeaderBasedOn(ApplicationRole.ADMIN.getFullRoleName());

    ActivityDetailsRequest activityDetailsRequest = getActivityDetailsRequest();
    ResponseEntity<Object> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.PUT,
        new HttpEntity<>(activityDetailsRequest, headers),
        Object.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

}
