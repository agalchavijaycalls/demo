package com.example.demo.dto.user;

import com.asoft.ainstitute.api.constant.Status;
import com.asoft.ainstitute.api.dto.BaseDto;
import com.asoft.ainstitute.api.dto.referencedata.Role;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class InstituteMemberRole extends BaseDto {

  private static final long serialVersionUID = 1L;

  private InstituteMember instituteMember;

  private Role role;

  private Status.InstituteMemberRoleStatus status;

  private String statusReason;

  private Date statusUpdatedDate;

  private Date createdDate;

  private String comment;

  public InstituteMember getInstituteMember() {
    return instituteMember;
  }

  public void setInstituteMember(InstituteMember instituteMember) {
    this.instituteMember = instituteMember;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
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

    InstituteMemberRole that = (InstituteMemberRole) o;

    return new EqualsBuilder().append(instituteMember, that.instituteMember).append(role, that.role).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(instituteMember).append(role).toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).appendSuper("BaseDto").append("role", role).append("status", status)
        .append("statusReason", statusReason).append("statusUpdatedDate", statusUpdatedDate)
        .append("createdDate", createdDate).append("comment", comment).toString();
  }
}
