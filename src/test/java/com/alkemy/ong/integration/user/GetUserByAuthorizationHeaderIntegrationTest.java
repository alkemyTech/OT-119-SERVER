package com.alkemy.ong.integration.user;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import com.alkemy.ong.common.AbstractBaseIntegrationTest;
import com.alkemy.ong.common.JwtUtils;
import com.alkemy.ong.config.security.ApplicationRole;
import com.alkemy.ong.exception.UserAlreadyExistException;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.request.UserDetailsRequest;
import com.alkemy.ong.model.response.UserResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GetUserInformationTest extends AbstractBaseIntegrationTest {

  private final String PATH = "/auth/me";
  @MockBean
  private JwtUtils jwtUtils;

  @Test
  public void shouldReturnUserAboutMeSuccessfully() throws UserAlreadyExistException {
    User user = stubUser(ApplicationRole.USER.getFullRoleName());
//    when();
    setAuthorizationHeaderBasedOn(ApplicationRole.USER.getFullRoleName());
//    to userDetailsRequest = new UserDetailsRequest();

    ResponseEntity<UserResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.GET,
        new HttpEntity<>(headers),
        UserResponse.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
  }
}
