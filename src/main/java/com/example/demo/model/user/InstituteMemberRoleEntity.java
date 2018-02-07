package com.example.demo.model.user;

import com.asoft.ainstitute.api.constant.Status;
import com.asoft.ainstitute.api.model.BaseEntity;
import com.asoft.ainstitute.api.model.referencedata.RoleEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "institute_member_role",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"institute_member_id", "role_id"})})
public class InstituteMemberRoleEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @JoinColumn(name = "institute_member_id", referencedColumnName = "id")
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private InstituteMemberEntity instituteMemberEntity;

  @JoinColumn(name = "role_id", referencedColumnName = "id")
  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  private RoleEntity roleEntity;

  @Basic(optional = false)
  @NotNull
  @Column(name = "status", nullable = false, length = 20)
  @Enumerated(EnumType.STRING)
  private Status.InstituteMemberRoleStatus status;

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

  @Size(max = 200)
  @Column(name = "comment", length = 200)
  private String comment;

  @PrePersist
  private void onCreate() {
    Date today = new Date();
    this.setCreatedDate(today);
    this.setStatusUpdatedDate(today);
  }

  public InstituteMemberEntity getInstituteMemberEntity() {
    return instituteMemberEntity;
  }

  public void setInstituteMemberEntity(InstituteMemberEntity instituteMemberEntity) {
    this.instituteMemberEntity = instituteMemberEntity;
  }

  public RoleEntity getRoleEntity() {
    return roleEntity;
  }

  public void setRoleEntity(RoleEntity roleEntity) {
    this.roleEntity = roleEntity;
  }

  public Status.InstituteMemberRoleStatus getStatus() {
    return status;
  }

  public void setStatus(Status.InstituteMemberRoleStatus status) {
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

    InstituteMemberRoleEntity that = (InstituteMemberRoleEntity) o;

    return new EqualsBuilder().append(instituteMemberEntity, that.instituteMemberEntity)
        .append(roleEntity, that.roleEntity).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(instituteMemberEntity).append(roleEntity).toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).appendSuper("BaseEntity").append("instituteMemberEntity", instituteMemberEntity)
        .append("roleEntity", roleEntity).append("status", status).append("statusReason", statusReason)
        .append("statusUpdatedDate", statusUpdatedDate).append("createdDate", createdDate).append("comment", comment)
        .toString();
  }
}
