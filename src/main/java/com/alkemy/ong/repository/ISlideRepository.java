package com.alkemy.ong.repository;

import com.alkemy.ong.model.entity.Slide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ISlideRepository extends JpaRepository<Slide, Long> {

  boolean existsByOrder(int order);

  @Query(value = "select coalesce(max(s.order), 0) from Slide as s")
  int getMaxSlideOrder();

}
