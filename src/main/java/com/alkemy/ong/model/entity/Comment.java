package com.alkemy.ong.model.entity;

import java.sql.Timestamp;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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

  @CreationTimestamp
  @Column(name = "TIMESTAMP")
  private Timestamp timestamp;

}
