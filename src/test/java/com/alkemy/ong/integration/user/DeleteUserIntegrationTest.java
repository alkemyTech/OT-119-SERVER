package com.alkemy.ong.integration.user;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.alkemy.ong.common.AbstractBaseIntegrationTest;
import com.alkemy.ong.config.security.ApplicationRole;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.response.ErrorResponse;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeleteUserIntegrationTest extends AbstractBaseIntegrationTest {

  private final String PATH = "/users/" + USER_ID;

  @Test
  public void shouldReturnForbiddenWhenUserIsNotUserRole() {
    setAuthorizationHeaderBasedOn(ApplicationRole.ADMIN.getFullRoleName());

    ResponseEntity<Object> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.DELETE,
        new HttpEntity<>(headers),
        Object.class);

    assertEquals(response.getStatusCode(), HttpStatus.FORBIDDEN);
  }

  @Test
  public void shouldReturnNotFoundWhenIdNoExist() {
    when(userRepository.findById(eq(USER_ID))).thenReturn(Optional.empty());
    setAuthorizationHeaderBasedOn(ApplicationRole.USER.getFullRoleName());

    ResponseEntity<ErrorResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.DELETE,
        new HttpEntity<>(headers),
        ErrorResponse.class);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("User not found.", response.getBody().getMessage());
  }

  @Test
  public void shouldSoftDeleteUserSuccessfully() {
    User user = stubUser(ApplicationRole.USER.getFullRoleName());
    when(userRepository.findById(eq(USER_ID))).thenReturn(Optional.of(user));
    when(userRepository.save(eq(user))).thenReturn(user);
    setAuthorizationHeaderBasedOn(ApplicationRole.USER.getFullRoleName());

    ResponseEntity<Object> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.DELETE,
        new HttpEntity<>(headers),
        Object.class);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
  }

}
