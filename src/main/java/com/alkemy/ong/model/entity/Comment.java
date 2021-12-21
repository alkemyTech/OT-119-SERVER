package com.alkemy.ong.model.entity;

import java.sql.Timestamp;
import javax.persistence.*;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "COMMENTS")
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "COMMENTS_ID", nullable = false)
  @Setter(AccessLevel.NONE)
  private long id;

  @Column(name = "BODY", nullable = false)
  private String body;


  @JoinColumn(name = "USERS_ID")
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  private User userId;

  @JoinColumn(name = "NEWS_ID")
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  private News newsId;

  @Basic(optional = false)
  @CreationTimestamp
  @Column(name = "TIMESTAMP")
  private Timestamp timestamp;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public User getUserId() {
    return userId;
  }

  public void setUserId(User userId) {
    this.userId = userId;
  }

  public News getNewsId() {
    return newsId;
  }

  public void setNewsId(News newsId) {
    this.newsId = newsId;
  }

  public Timestamp getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Timestamp timestamp) {
    this.timestamp = timestamp;
  }
}
