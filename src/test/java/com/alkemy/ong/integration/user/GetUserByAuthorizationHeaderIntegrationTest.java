package com.alkemy.ong.integration.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import com.alkemy.ong.common.AbstractBaseIntegrationTest;
import com.alkemy.ong.common.JwtUtils;
import com.alkemy.ong.common.SecurityTestConfig;
import com.alkemy.ong.config.security.ApplicationRole;
import com.alkemy.ong.exception.UserAlreadyExistException;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.response.ErrorResponse;
import com.alkemy.ong.model.response.UserResponse;
import org.apache.tomcat.util.http.parser.Authorization;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GetUserByAuthorizationHeaderIntegrationTest extends AbstractBaseIntegrationTest {

  private final String PATH = "/auth/me";
  @Autowired
  private JwtUtils jwtUtils;
  private String token = SecurityTestConfig.createToken("johnny@gmail.com",
      ApplicationRole.USER.getFullRoleName());

  @Test
  public void shouldReturnUserAboutMeSuccessfully() {
    User user = stubUser(ApplicationRole.USER.getFullRoleName());
    when(jwtUtils.extractUsername(token)).thenReturn(user.getEmail());
    when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
    setAuthorizationHeaderBasedOn(ApplicationRole.USER.getFullRoleName());

    ResponseEntity<UserResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.GET,
        new HttpEntity<>(token, headers),
        UserResponse.class);

    assertNotNull(response.getBody());
    assertEquals(response.getBody().getEmail(), user.getEmail());
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  public void shouldReturnBadRequestWhenUserDoesNotExist() {
    when(jwtUtils.extractUsername(token)).thenReturn(null);
    setAuthorizationHeaderBasedOn(ApplicationRole.USER.getFullRoleName());

    ResponseEntity<ErrorResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.GET,
        new HttpEntity<>(token, headers),
        ErrorResponse.class);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }
}
