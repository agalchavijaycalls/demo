package com.example.demo.dto.referencedata;

import com.asoft.ainstitute.api.constant.Status;
import com.asoft.ainstitute.api.dto.BaseDto;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class RolePermission extends BaseDto {

  private static final long serialVersionUID = 1L;

  private Role role;

  private Permission permission;

  private Status.RolePermissionStatus status;

  private String statusReason;

  private Date statusUpdatedDate;

  private Date createdDate;

  private String comment;

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public Permission getPermission() {
    return permission;
  }

  public void setPermission(Permission permission) {
    this.permission = permission;
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

    RolePermission that = (RolePermission) o;

    return new EqualsBuilder().append(role, that.role).append(permission, that.permission).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(role).append(permission).toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).appendSuper("BaseDto").append("role", role).append("permission", permission)
        .append("status", status).append("statusReason", statusReason).append("statusUpdatedDate", statusUpdatedDate)
        .append("createdDate", createdDate).append("comment", comment).toString();
  }
}
