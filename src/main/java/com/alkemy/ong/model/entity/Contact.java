package com.alkemy.ong.model.entity;

import javax.validation.constraints.Email;
import lombok.*;
import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CONTACTS")
public class Contact {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Setter(AccessLevel.NONE)
  @Column(name = "CONTACTS_ID")
  private long id;

  @Column(name = "NAME", nullable = false)
  private String name;

  @Column(name = "PHONE", nullable = false)
  private String phone;

  @Column(name = "EMAIL", nullable = false)
  private String email;

  @Column(name = "MESSAGE", nullable = false)
  private String message;

  @Column(name = "DELETED_AT")
  private boolean deletedAt;
}
