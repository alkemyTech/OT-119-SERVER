package com.alkemy.ong.repository;

import com.alkemy.ong.model.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INewRepository extends JpaRepository<News, Long> {

}
