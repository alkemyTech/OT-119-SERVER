package com.alkemy.ong.service;

import com.alkemy.ong.common.EntityUtils;
import com.alkemy.ong.common.ImageUtils;
import com.alkemy.ong.exception.ThirdPartyException;
import com.alkemy.ong.model.entity.Organization;
import com.alkemy.ong.model.entity.Slide;
import com.alkemy.ong.model.request.SlideRequest;
import com.alkemy.ong.model.response.ListSlideResponse;
import com.alkemy.ong.model.response.SlideResponse;
import com.alkemy.ong.repository.ISlideRepository;
import com.alkemy.ong.service.abstraction.ICreateSlideService;
import com.alkemy.ong.service.abstraction.IDeleteSlideService;
import com.alkemy.ong.service.abstraction.IGetOrganizationService;
import com.alkemy.ong.service.abstraction.IGetSlideService;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class SlideServiceImpl implements IDeleteSlideService, IGetSlideService,
    ICreateSlideService {

  private static final String SLIDE_NOT_FOUND_MESSAGE = "Slide not found.";
  @Autowired
  ImageUtils imageUtils;
  @Autowired
  IGetOrganizationService getOrganizationService;
  @Autowired
  private ISlideRepository slideRepository;

  @Override
  public void delete(Long id) throws EntityNotFoundException {
    if (!slideRepository.existsById(id)) {
      throw new EntityNotFoundException(SLIDE_NOT_FOUND_MESSAGE);
    }
    slideRepository.deleteById(id);
  }

  @Override
  public ListSlideResponse getAll() {
    List<Slide> slides = slideRepository.findAll();
    ListSlideResponse slideResponses = new ListSlideResponse();
    slideResponses.setSlides(EntityUtils.convertTo(slides));
    return slideResponses;
  }

  @Override
  public SlideResponse getBy(Long id) {
    Optional<Slide> slideOptional = slideRepository.findById(id);
    if (slideOptional.isEmpty()) {
      throw new EntityNotFoundException(SLIDE_NOT_FOUND_MESSAGE);
    }
    return EntityUtils.convertToSlideDetailsResponse(slideOptional.get());
  }

  private InputStream convertToInputStream(String encodedString) {
    byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
    return new ByteArrayInputStream(decodedBytes);
  }

  @Override
  public SlideResponse create(SlideRequest slideRequest) throws ThirdPartyException {
    String fileName = (slideRequest.getFileName() == null || slideRequest.getFileName().isEmpty())
        ? UUID.randomUUID().toString() : slideRequest.getFileName();
    String imageUrl = imageUtils.upload(convertToInputStream(slideRequest.getEncodedImage()),
        fileName, slideRequest.getContentType());
    Organization organization = getOrganizationService.getOrganization();
    Slide slide = new Slide();
    slide.setImageUrl(imageUrl);
    slide.setText(slideRequest.getText());
    slide.setOrder(slideRequest.getOrder());
    slide.setOrganization(organization);
    return EntityUtils.convertToSlideDetailsResponse(slideRepository.save(slide));
  }
}

