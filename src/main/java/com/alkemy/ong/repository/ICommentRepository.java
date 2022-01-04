package com.alkemy.ong.repository;

import com.alkemy.ong.model.entity.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, Long> {


  @Query(value = "SELECT * FROM comments WHERE news_id = :newsId",
  nativeQuery = true)
  List<Comment> findAllCommentsByNewsId(@Param("newsId") Long newsId);

  @Transactional
  @Modifying
  @Query(value = "update Comment u set u.body = :body where u.id = :id")
  void updateComment(@Param(value = "body") String body, @Param(value = "id") long id);

  @Query(value = "from Comment c where c.newsId.id = :id")
  List<Comment> findAllCommentsByNewsId(@Param("id") Long id);


}
