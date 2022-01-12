package com.alkemy.ong.integration.organization;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import com.alkemy.ong.common.AbstractBaseIntegrationTest;
import com.alkemy.ong.model.entity.Organization;
import com.alkemy.ong.model.entity.Slide;
import com.alkemy.ong.model.response.OrganizationResponse;
import com.alkemy.ong.model.response.SlideResponse;
import com.alkemy.ong.repository.IOrganizationRepository;
import com.alkemy.ong.repository.ISlideRepository;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
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
public class GetOrganizationDetailsIntegrationTest extends AbstractBaseIntegrationTest {

  private static final String PATH = "/organization/public";

  @MockBean
  private ISlideRepository slideRepository;

  @MockBean
  private IOrganizationRepository organizationRepository;

  private final List<Organization> organizationsStub = new ArrayList<>();
  private final List<Slide> slidesStub = new ArrayList<>();

  @Test
  public void shouldReturnOkWhenAccessedWithoutRole() {
    setupStubs();

    when(organizationRepository.findAll()).thenReturn(organizationsStub);
    when(slideRepository.findAll()).thenReturn(slidesStub);

    ResponseEntity<OrganizationResponse> response = restTemplate.exchange(
        createURLWithPort(PATH),
        HttpMethod.GET,
        new HttpEntity<>(headers),
        OrganizationResponse.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    OrganizationResponse responseBody = response.getBody();
    assertNotNull(responseBody);
    assertEquals(organizationsStub.get(0).getEmail(), responseBody.getEmail());
    assertEquals(organizationsStub.get(0).getImage(), responseBody.getImage());
    assertEquals(organizationsStub.get(0).getAddress(), responseBody.getAddress());
    assertEquals(organizationsStub.get(0).getPhone(), responseBody.getPhone());

    List<SlideResponse> slideResponses = responseBody.getSlides();
    assertEquals(slidesStub.size(), slideResponses.size());

    for (int i = 0; i < slideResponses.size(); i++) {
      SlideResponse slideResponse = slideResponses.get(i);
      assertEquals(slidesStub.get(i).getImageUrl(), slideResponse.getImageUrl());
      assertEquals(slidesStub.get(i).getOrder(), slideResponse.getOrder());
    }
  }

  private void setupStubs() {
    organizationsStub.add(createOrganization());

    slidesStub.add(createSlide("/foo.png", "Slide 1 text", 1));
    slidesStub.add(createSlide("/bar.jpg", "Slide 2 text", 2));
  }

  private Organization createOrganization() {
    Organization organization = new Organization();
    organization.setAboutUsText("About Us");
    organization.setImage("http://foo.png");
    organization.setAddress("Address 123");
    organization.setEmail("somos.mas@outlook.com");
    organization.setPhone(456);
    organization.setTimeStamp(Timestamp.from(Instant.now()));
    return organization;
  }

  private Slide createSlide(String imageUrl, String text, int order) {
    Slide slide = new Slide();
    slide.setImageUrl(imageUrl);
    slide.setText(text);
    slide.setOrder(order);
    return slide;
  }
}
