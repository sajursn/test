package com.travanleo.user.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Employee DTO
 * 
 *
 */
public class EmployeeDTO extends BaseDTO {

  /**
   * 
   */
  private static final long serialVersionUID = -8067210052397923732L;

  @NotNull(message = "First Name cannot be null")
  String firstName;
  @NotNull(message = "Last Name cannot be null")
  String lastName;
  @NotNull(message = "DOB cannot be null")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
  @JsonSerialize(using = LocalDateSerializer.class)
  LocalDate dateOfBirth;
  @NotNull(message = "Emp ID cannot be null")
  Long employeeId;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public Long getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(Long employeeId) {
    this.employeeId = employeeId;
  }

}
