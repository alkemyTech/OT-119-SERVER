package com.alkemy.ong.integration.organization;

import static org.junit.Assert.assertEquals;

import com.alkemy.ong.common.AbstractBaseIntegrationTest;
import com.alkemy.ong.service.OrganizationServiceImpl;
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
public class OrganizationPublicEndpointIntegrationTest extends AbstractBaseIntegrationTest {

  private final String PATH = "/organization/public";

  @MockBean
  OrganizationServiceImpl organizationService;

  @Test
  public void shouldReturnOkWhenAccessedWithoutRole() throws Exception {
    ResponseEntity<Object> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.GET,
        new HttpEntity<>(headers),
        Object.class);

    assertEquals(response.getStatusCode(), HttpStatus.OK);
  }
}
