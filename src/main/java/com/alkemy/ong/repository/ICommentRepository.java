package com.alkemy.ong.repository;

import com.alkemy.ong.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByOrderByTimestampAsc();
    List<Comment> findCommentsByNewsId(Long news_id);
    Comment findCommentByUserIdAndId(User user_id, Long comment_id);
}
