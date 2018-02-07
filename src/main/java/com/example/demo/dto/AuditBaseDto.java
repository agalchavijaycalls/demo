package com.example.demo.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public abstract class AuditBaseDto extends BaseDto {

  private static final long serialVersionUID = 1L;

  private Date createdDate;

  private String createdBy;

  private Date updatedDate;

  private String updatedBy;

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date dateCreated) {
    this.createdDate = dateCreated;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public Date getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(Date dateUpdated) {
    this.updatedDate = dateUpdated;
  }

  public String getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).appendSuper("BaseDto").append("createdDate", createdDate)
        .append("createdBy", createdBy).append("updatedDate", updatedDate).append("updatedBy", updatedBy).toString();
  }
}