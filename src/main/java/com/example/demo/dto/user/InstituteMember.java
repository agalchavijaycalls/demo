package com.example.demo.dto.user;

import com.asoft.ainstitute.api.constant.Status;
import com.asoft.ainstitute.api.dto.BaseDto;
import com.asoft.ainstitute.api.dto.institute.Institute;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;
import java.util.List;

public class InstituteMember extends BaseDto {

  private static final long serialVersionUID = 1L;

  private UserInfo userInfo;

  private Institute institute;

  private Status.InstituteMemberStatus status;

  private String statusReason;

  private Date statusUpdatedDate;

  private Date createdDate;

  private String comment;

  private List<InstituteMemberRole> instituteMemberRoles;

  public UserInfo getUserInfo() {
    return userInfo;
  }

  public void setUserInfo(UserInfo userInfo) {
    this.userInfo = userInfo;
  }

  public Institute getInstitute() {
    return institute;
  }

  public void setInstitute(Institute institute) {
    this.institute = institute;
  }

  public Status.InstituteMemberStatus getStatus() {
    return status;
  }

  public void setStatus(Status.InstituteMemberStatus status) {
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

  public List<InstituteMemberRole> getInstituteMemberRoles() {
    return instituteMemberRoles;
  }

  public void setInstituteMemberRoles(List<InstituteMemberRole> instituteMemberRoles) {
    this.instituteMemberRoles = instituteMemberRoles;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    InstituteMember that = (InstituteMember) o;

    return new EqualsBuilder().append(userInfo, that.userInfo).append(institute, that.institute).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(userInfo).append(institute).toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).appendSuper("BaseDto").append("userInfo", userInfo).append("institute", institute)
        .append("status", status).append("statusReason", statusReason).append("statusUpdatedDate", statusUpdatedDate)
        .append("createdDate", createdDate).append("comment", comment)
        .append("instituteMemberRoles", instituteMemberRoles).toString();
  }
}
