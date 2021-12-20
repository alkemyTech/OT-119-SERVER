package com.alkemy.ong.repository;

import com.alkemy.ong.model.entity.New;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INewRepository extends JpaRepository<New, Long> {

}
