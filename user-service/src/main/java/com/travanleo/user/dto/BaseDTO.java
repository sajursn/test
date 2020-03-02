package com.travanleo.user.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Base DTO for entities.
 * 
 *
 */
public abstract class BaseDTO implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 4892429106877616449L;

  private Long id;

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

}
