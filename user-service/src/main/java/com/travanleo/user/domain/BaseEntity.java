package com.travanleo.user.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


@MappedSuperclass
public abstract class BaseEntity implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 4892429106877616449L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Version
  int version;

  String createdBy;

  String modifiedBy;

  LocalDateTime modifiedDateTime;

  LocalDateTime createdDateTime;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public String getModifiedBy() {
    return modifiedBy;
  }

  public void setModifiedBy(String modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  public LocalDateTime getModifiedDateTime() {
    return modifiedDateTime;
  }

  public void setModifiedDateTime(LocalDateTime modifiedDateTime) {
    this.modifiedDateTime = modifiedDateTime;
  }

  public LocalDateTime getCreatedDateTime() {
    return createdDateTime;
  }

  public void setCreatedDateTime(LocalDateTime createdDateTime) {
    this.createdDateTime = createdDateTime;
  }

  @PrePersist
  public void prePersist() {
    this.createdDateTime = LocalDateTime.now();
    this.createdBy = getUserDetails();
  }

  @PreUpdate
  public void preUpdate() {
    this.modifiedDateTime = LocalDateTime.now();
    this.modifiedBy = getUserDetails();
  }

  /**
   * Fetches user details from spring security context holder
   * @return
   */
  private String getUserDetails() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (!(authentication instanceof AnonymousAuthenticationToken) && authentication != null) {
      return authentication.getName();

    }
    return StringUtils.EMPTY;
  }

}
