package com.example.demo.model.referencedata;

import com.asoft.ainstitute.api.constant.Status;
import com.asoft.ainstitute.api.model.BaseEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "role_permission", uniqueConstraints = {@UniqueConstraint(columnNames = {"role_id", "permission_id"})})
public class RolePermissionEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @JoinColumn(name = "role_id", referencedColumnName = "id")
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private RoleEntity roleEntity;

  @JoinColumn(name = "permission_id", referencedColumnName = "id")
  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  private PermissionEntity permissionEntity;

  @Basic(optional = false)
  @NotNull
  @Column(name = "status", nullable = false, length = 20)
  @Enumerated(EnumType.STRING)
  private Status.RolePermissionStatus status;

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

  public RoleEntity getRoleEntity() {
    return roleEntity;
  }

  public void setRoleEntity(RoleEntity roleEntity) {
    this.roleEntity = roleEntity;
  }

  public PermissionEntity getPermissionEntity() {
    return permissionEntity;
  }

  public void setPermissionEntity(PermissionEntity permissionEntity) {
    this.permissionEntity = permissionEntity;
  }

  public Status.RolePermissionStatus getStatus() {
    return status;
  }

  public void setStatus(Status.RolePermissionStatus status) {
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

    RolePermissionEntity that = (RolePermissionEntity) o;

    return new EqualsBuilder().append(roleEntity, that.roleEntity).append(permissionEntity, that.permissionEntity)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(roleEntity).append(permissionEntity).toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).appendSuper("BaseEntity").append("roleEntity", roleEntity)
        .append("permissionEntity", permissionEntity).append("status", status).append("statusReason", statusReason)
        .append("statusUpdatedDate", statusUpdatedDate).append("createdDate", createdDate).append("comment", comment)
        .toString();
  }
}
