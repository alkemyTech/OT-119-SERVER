package com.alkemy.ong.repository;

import com.alkemy.ong.model.entity.Slide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ISlideRepository extends JpaRepository<Slide, Long> {
  boolean existsByOrder(int order);
  @Query(value = "SELECT MAX(slide_order) FROM slides", nativeQuery = true)
  Integer getMaxtSlideOrder();
}
