package com.alkemy.ong.service;

import com.alkemy.ong.common.EntityUtils;
import com.alkemy.ong.common.ImageUtils;
import com.alkemy.ong.exception.InvalidArgumentException;
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
import org.springframework.util.StringUtils;

@Service

public class SlideServiceImpl implements IDeleteSlideService, IGetSlideService,
    ICreateSlideService {

  private static final String SLIDE_NOT_FOUND_MESSAGE = "Slide not found.";
  @Autowired
  private ImageUtils imageUtils;
  @Autowired
  private IGetOrganizationService getOrganizationService;
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

  private InputStream convertTo(String encodedString) {
    byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
    return new ByteArrayInputStream(decodedBytes);
  }

  private String getFilenameOrDefault(String fileName) {
    return (StringUtils.hasText(fileName)) ? fileName : UUID.randomUUID().toString();
  }

  private int decideSlideOrder(int requestedOrder) throws InvalidArgumentException {
    int decidedOrder = 0;

    if (requestedOrder == 0) {
      decidedOrder = slideRepository.getMaxtSlideOrder() + 1;
    } else if (slideRepository.existsByOrder(requestedOrder)) {
      throw new InvalidArgumentException("A slide is already using the specified order number.");
    } else {
      decidedOrder = requestedOrder;
    }
    return decidedOrder;
  }

  private Slide buildSlide(String imageUrl, String text, int order) {
    Organization organization = getOrganizationService.getOrganization();
    Slide slide = new Slide();
    slide.setImageUrl(imageUrl);
    slide.setText(text);
    slide.setOrder(order);
    slide.setOrganization(organization);
    return slide;
  }

  @Override
  public SlideResponse create(SlideRequest slideRequest)
      throws ThirdPartyException, InvalidArgumentException {
    String fileName = getFilenameOrDefault(slideRequest.getFileName());
    String imageUrl = imageUtils.upload(convertTo(slideRequest.getEncodedImage()),
        fileName, slideRequest.getContentType());
    int slideOrder = decideSlideOrder(slideRequest.getOrder());
    Slide slide = buildSlide(imageUrl, slideRequest.getText(), slideOrder);
    return EntityUtils.convertToSlideDetailsResponse(slideRepository.save(slide));
  }
}

