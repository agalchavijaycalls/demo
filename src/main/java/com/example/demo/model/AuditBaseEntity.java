package com.example.demo.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@MappedSuperclass
public abstract class AuditBaseEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Column(name = "created_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdDate;

  @Size(max = 20)
  @Column(name = "created_by", length = 20)
  private String createdBy;

  @Column(name = "updated_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date updatedDate;

  @Size(max = 20)
  @Column(name = "updated_by", length = 20)
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

  @PrePersist
  public void onPrePersist() {
    this.createdDate = new Date();
  }

  @PreUpdate
  public void onPreUpdate() {
    this.updatedDate = new Date();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).appendSuper("BaseEntity").append("createdDate", createdDate)
        .append("createdBy", createdBy).append("updatedDate", updatedDate).append("updatedBy", updatedBy).toString();
  }
}