package com.alkemy.ong.repository;

import com.alkemy.ong.model.entity.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, Long> {

  @Query(value = "SELECT * FROM comments WHERE news_id = :newsId",
  nativeQuery = true)
  List<Comment> findAllCommentsByNewsId(@Param("newsId") Long newsId);

}
