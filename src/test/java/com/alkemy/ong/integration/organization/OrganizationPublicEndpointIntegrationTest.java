package com.alkemy.ong.integration.organization;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alkemy.ong.common.JwtUtils;
import com.alkemy.ong.controller.OrganizationController;
import com.alkemy.ong.model.response.OrganizationResponse;
import com.alkemy.ong.service.OrganizationServiceImpl;
import com.alkemy.ong.service.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(OrganizationController.class)
@RunWith(SpringRunner.class)
public class OrganizationPublicEndpointIntegrationTest {

  private final String PATH = "/organization/public";

  @Autowired
  MockMvc mockMvc;

  @MockBean
  OrganizationServiceImpl organizationService;

  @MockBean
  UserServiceImpl userService;

  @MockBean
  JwtUtils jwtUtils;

  @Test
  public void shouldReturnOkWhenAccessedWithoutRole() throws Exception {
    Mockito.when(organizationService.getOrganizationWithSlides())
        .thenReturn(new OrganizationResponse());
    mockMvc.perform(MockMvcRequestBuilders
            .get(PATH)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }
}
