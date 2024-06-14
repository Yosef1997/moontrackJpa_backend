package com.yosef.moontrackJpa.users.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity
@Table(name = "users", schema = "moontrack")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Users {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "moontrack.users_id_gen")
  @SequenceGenerator(name = "moontrack.users_id_gen", sequenceName = "moontrack.users_id_seq", allocationSize = 1)
  @Column(name = "id", nullable = false)
  private Long id;

  @Size(max = 255)
  @NotNull
  @Column(name = "name", nullable = false, length = 150)
  private String name;

  @Size(max = 150)
  @NotNull
  @Column(name = "email", nullable = false, length = 150)
  private String email;

  @JsonIgnore
  @Size(max = 100)
  @NotNull
  @Column(name = "password", nullable = false, length = 100)
  private String password;

  @JsonIgnore
  @Size(max = 4)
  @Column(name = "pin", length = 4)
  private String pin;

  @Size(max = 255)
  @Column(name = "avatar", length = 255)
  private String avatar;

  @Size(max = 150)
  @Column(name = "quotes", length = 150)
  private String quotes;

  @NotNull
  @ColumnDefault("1")
  @Column(name = "active_language", nullable = false)
  private int activeLanguage;

  @NotNull
  @ColumnDefault("1")
  @Column(name = "active_currency", nullable = false)
  private int activeCurrency;

  @NotNull
  @ColumnDefault("CURRENT_TIMESTAMP")
  @Column(name = "created_at", nullable = false)
  private Instant createdAt;

  @NotNull
  @ColumnDefault("CURRENT_TIMESTAMP")
  @Column(name = "updated_at", nullable = false)
  private Instant updatedAt;

  @Column(name = "deleted_at")
  private Instant deletedAt;

  @PrePersist
  public void prePersist() {
    this.createdAt = Instant.now();
    this.updatedAt = Instant.now();
  }

  @PreUpdate
  public void preUpdate() {
    this.updatedAt = Instant.now();
  }

  @PreRemove
  public void preRemove() {
    this.deletedAt = Instant.now();
  }
}
