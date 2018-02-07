package com.example.demo.model.institute;

import com.asoft.ainstitute.api.constant.Status;
import com.asoft.ainstitute.api.model.BaseEntity;
import com.asoft.ainstitute.api.model.referencedata.InstituteTypeEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "institute", uniqueConstraints = {@UniqueConstraint(columnNames = {"code"})})
public class InstituteEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Basic(optional = false)
  @NotNull
  @Size(min = 5, max = 20)
  @Column(name = "code", nullable = false, length = 20)
  private String code;

  @Basic(optional = false)
  @NotNull
  @Size(min = 10, max = 200)
  @Column(name = "name", nullable = false, length = 200)
  private String name;

  @JoinColumn(name = "institute_type_id", referencedColumnName = "id")
  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  private InstituteTypeEntity instituteTypeEntity;

  @Basic(optional = false)
  @NotNull
  @Column(name = "status", nullable = false, length = 20)
  @Enumerated(EnumType.STRING)
  private Status.InstituteStatus status;

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
    this.setUpdatedDate(today);
    this.setStatusUpdatedDate(today);
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public InstituteTypeEntity getInstituteTypeEntity() {
    return instituteTypeEntity;
  }

  public void setInstituteTypeEntity(InstituteTypeEntity typeEntity) {
    this.instituteTypeEntity = typeEntity;
  }

  public Status.InstituteStatus getStatus() {
    return status;
  }

  public void setStatus(Status.InstituteStatus status) {
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

  public void setComment(String remark) {
    this.comment = remark;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    InstituteEntity that = (InstituteEntity) o;

    return new EqualsBuilder().append(code, that.code).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(code).toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).appendSuper("BaseEntity").append("code", code).append("name", name)
        .append("instituteTypeEntity", instituteTypeEntity).append("status", status)
        .append("statusReason", statusReason).append("statusUpdatedDate", statusUpdatedDate)
        .append("createdDate", createdDate).append("updatedDate", updatedDate).append("comment", comment).toString();
  }
}
