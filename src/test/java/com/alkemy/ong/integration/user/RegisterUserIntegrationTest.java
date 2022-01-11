package com.alkemy.ong.integration.user;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.alkemy.ong.common.AbstractBaseIntegrationTest;
import com.alkemy.ong.common.JwtUtils;
import com.alkemy.ong.common.SecurityTestConfig;
import com.alkemy.ong.common.mail.template.EmailAddress;
import com.alkemy.ong.config.security.ApplicationRole;
import com.alkemy.ong.exception.SendEmailException;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.request.UserDetailsRequest;
import com.alkemy.ong.model.response.ErrorResponse;
import com.alkemy.ong.model.response.UserDetailsResponse;
import com.alkemy.ong.service.WelcomeEmailService;
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
public class RegisterUserIntegrationTest extends AbstractBaseIntegrationTest {

  private static final String PATH = "/auth/register";
  private String token = SecurityTestConfig.createToken(
      "johnny@gmail.com",
      ApplicationRole.USER.getFullRoleName());

  @MockBean
  private WelcomeEmailService welcomeEmailService;
  @MockBean
  private JwtUtils jwtUtils;

  @Test
  public void shouldRegisterUserWithTokenSuccessfully()
      throws SendEmailException {
    User user = stubUser(ApplicationRole.USER.getFullRoleName());
    when(userRepository.findByEmail(eq("johnny@gmail.com"))).thenReturn(null);
    when(userRepository.save(isA(User.class))).thenReturn(user);
    when(jwtUtils.generateToken(eq(user))).thenReturn(token);
    doNothing().when(welcomeEmailService).sendEmail(isA(EmailAddress.class));
    UserDetailsRequest userDetailsRequest = getUserDetailsRequest();

    ResponseEntity<UserDetailsResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.POST,
        new HttpEntity<>(userDetailsRequest, headers),
        UserDetailsResponse.class);

    assertNotNull(response.getBody().getToken());
    assertEquals(response.getBody().getEmail(), user.getEmail());
    assertEquals(response.getBody().getFirstName(), user.getFirstName());
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
  }

  @Test
  public void shouldReturnEmailNotAvailable() {
    User user = stubUser(ApplicationRole.USER.getFullRoleName());
    when(userRepository.findByEmail(eq("johnny@gmail.com"))).thenReturn(user);
    UserDetailsRequest userDetailsRequest = getUserDetailsRequest();

    ResponseEntity<ErrorResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.POST,
        new HttpEntity<>(userDetailsRequest, headers),
        ErrorResponse.class);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals("Email address is already used.", response.getBody().getMessage());
  }

  private UserDetailsRequest getUserDetailsRequest() {
    UserDetailsRequest userDetailsRequest = new UserDetailsRequest();
    userDetailsRequest.setEmail("johnny@gmail.com");
    userDetailsRequest.setFirstName("john");
    userDetailsRequest.setLastName("Doe");
    userDetailsRequest.setPassword("123456789");
    return userDetailsRequest;
  }
}
