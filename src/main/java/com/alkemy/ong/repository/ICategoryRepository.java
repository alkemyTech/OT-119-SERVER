package com.alkemy.ong.repository;

import com.alkemy.ong.model.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long>,
    PagingAndSortingRepository<Category, Long> {

  @Override
  Page<Category> findAll(Pageable pageable);
}
