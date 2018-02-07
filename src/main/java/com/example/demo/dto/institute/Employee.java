package com.example.demo.dto.institute;

import com.asoft.ainstitute.api.constant.Status;
import com.asoft.ainstitute.api.dto.BaseDto;
import com.asoft.ainstitute.api.dto.user.InstituteMember;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class Employee extends BaseDto {

  private static final long serialVersionUID = 1L;

  private String code;

  private InstituteMember instituteMember;

  private Status.EmployeeStatus status;

  private String statusReason;

  private Date statusUpdatedDate;

  private Date createdDate;

  private Date updatedDate;

  private String comment;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public InstituteMember getInstituteMember() {
    return instituteMember;
  }

  public void setInstituteMember(InstituteMember instituteMember) {
    this.instituteMember = instituteMember;
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

    Employee that = (Employee) o;

    return new EqualsBuilder().append(instituteMember, that.instituteMember).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(instituteMember).toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).appendSuper("BaseDto").append("code", code)
        .append("instituteMember", instituteMember).append("status", status).append("statusReason", statusReason)
        .append("statusUpdatedDate", statusUpdatedDate).append("createdDate", createdDate)
        .append("updatedDate", updatedDate).append("comment", comment).toString();
  }
}
