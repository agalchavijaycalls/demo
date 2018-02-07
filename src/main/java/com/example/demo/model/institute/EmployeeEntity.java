package com.example.demo.model.institute;

import com.asoft.ainstitute.api.constant.Status;
import com.asoft.ainstitute.api.model.BaseEntity;
import com.asoft.ainstitute.api.model.user.InstituteMemberEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "employee")
public class EmployeeEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Basic(optional = false)
  @NotNull
  @Size(min = 5, max = 20)
  @Column(name = "code", nullable = false, length = 20)
  private String code;

  @PrimaryKeyJoinColumn(name = "institute_member_id", referencedColumnName = "id")
  @OneToOne(optional = false, fetch = FetchType.LAZY)
  private InstituteMemberEntity instituteMemberEntity;

  @Basic(optional = false)
  @NotNull
  @Column(name = "status", nullable = false, length = 20)
  @Enumerated(EnumType.STRING)
  private Status.EmployeeStatus status;

  @Size(max = 100)
  @Column(name = "status_reason", length = 100)
  private String statusReason;

  @Basic(optional = false)
  @NotNull
  @Column(name = "status_updated_date", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date statusUpdatedDate;

  @Basic(optional = false)
  @NotNull
  @Column(name = "created_date", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdDate;

  @Basic(optional = false)
  @NotNull
  @Column(name = "updated_date", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date updatedDate;

  @Size(max = 200)
  @Column(name = "comment", length = 200)
  private String comment;

  @PrePersist
  private void onCreate() {
    Date today = new Date();
    this.setCreatedDate(today);
    this.setStatusUpdatedDate(today);
    this.setUpdatedDate(today);
  }

  @PreUpdate
  private void onUpdate() {
    this.setUpdatedDate(new Date());
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public InstituteMemberEntity getInstituteMemberEntity() {
    return instituteMemberEntity;
  }

  public void setInstituteMemberEntity(InstituteMemberEntity instituteMemberEntity) {
    this.instituteMemberEntity = instituteMemberEntity;
  }

  public Status.EmployeeStatus getStatus() {
    return status;
  }

  public void setStatus(Status.EmployeeStatus status) {
    this.status = status;
  }

  public String getStatusReason() {
    return statusReason;
  }

  public void setStatusReason(String statusReason) {
    this.statusReason = statusReason;
  }

  public Date getStatusUpdatedDate() {
    return statusUpdatedDate;
  }

  public void setStatusUpdatedDate(Date dateStatusUpdated) {
    this.statusUpdatedDate = dateStatusUpdated;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date dateCreated) {
    this.createdDate = dateCreated;
  }

  public Date getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(Date dateUpdated) {
    this.updatedDate = dateUpdated;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    EmployeeEntity that = (EmployeeEntity) o;

    return new EqualsBuilder().append(instituteMemberEntity, that.instituteMemberEntity).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(instituteMemberEntity).toHashCode();
  }

  @Override
  public String toString() {
    return "Employee{" + "code='" + code + '\'' + ", instituteMemberEntity=" + instituteMemberEntity + ", status="
        + status + ", statusReason='" + statusReason + '\'' + ", statusUpdatedDate=" + statusUpdatedDate + '}';
  }
}
