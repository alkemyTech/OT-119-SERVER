package com.alkemy.ong.integration.organization;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.alkemy.ong.common.AbstractBaseIntegrationTest;
import com.alkemy.ong.model.entity.Organization;
import com.alkemy.ong.model.entity.Slide;
import com.alkemy.ong.repository.IOrganizationRepository;
import com.alkemy.ong.repository.ISlideRepository;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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
  private ISlideRepository slideRepository;

  @MockBean
  private IOrganizationRepository organizationRepository;

  private ArrayList<Organization> organizationsStub = new ArrayList<>();
  private List<Slide> slidesStub = new ArrayList<>();

  private void setupStubs() {
    Organization organization = new Organization();
    organization.setAboutUsText("About Us");
    organization.setImage("http://foo.png");
    organization.setAddress("Calle 123");
    organization.setEmail("somos.mas@outlook.com");
    organization.setPhone(456);
    organization.setTimeStamp(Timestamp.from(Instant.now()));
    organizationsStub.add(organization);

    Slide slide1 = new Slide();
    slide1.setImageUrl("/foo.png");
    slide1.setText("Slide 1 text");
    slide1.setOrder(1);

    Slide slide2 = new Slide();
    slide2.setImageUrl("/bar.jpg");
    slide2.setText("Slide 2 text");
    slide2.setOrder(2);

    slidesStub.add(slide1);
    slidesStub.add(slide2);
  }

  @Test
  public void shouldReturnOkWhenAccessedWithoutRole() {
    setupStubs();

    Mockito.when(organizationRepository.findAll()).thenReturn(organizationsStub);
    Mockito.when(slideRepository.findAll()).thenReturn(slidesStub);

    ResponseEntity<Object> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.GET,
        new HttpEntity<>(headers),
        Object.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    LinkedHashMap responseBody = (LinkedHashMap) response.getBody();
    assertNotNull(responseBody);
    assertEquals(organizationsStub.get(0).getEmail(), responseBody.get("email"));
    assertEquals(organizationsStub.get(0).getImage(), responseBody.get("image"));
    assertEquals(organizationsStub.get(0).getAddress(), responseBody.get("address"));
    assertEquals(organizationsStub.get(0).getPhone(), responseBody.get("phone"));

    List slidesResponse = (List) responseBody.get("slides");

    assertEquals(slidesStub.size(), slidesResponse.size());

    for (int i = 0; i < slidesResponse.size(); i++) {
      HashMap slideAttributResponse = ((HashMap) slidesResponse.get(i));
      assertEquals(slidesStub.get(i).getImageUrl(), slideAttributResponse.get("imageUrl"));
      assertEquals(slidesStub.get(i).getOrder(), slideAttributResponse.get("order"));
    }
  }
}
