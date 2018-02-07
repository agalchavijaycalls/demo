package com.example.demo.dto.institute;

import com.asoft.ainstitute.api.constant.Status;
import com.asoft.ainstitute.api.dto.BaseDto;
import com.asoft.ainstitute.api.dto.referencedata.InstituteType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class Institute extends BaseDto {

  private static final long serialVersionUID = 1L;

  private String code;

  private String name;

  private InstituteType instituteType;

  private Status.InstituteStatus status;

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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public InstituteType getInstituteType() {
    return instituteType;
  }

  public void setInstituteType(InstituteType instituteType) {
    this.instituteType = instituteType;
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

    Institute that = (Institute) o;

    return new EqualsBuilder().append(code, that.code).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(code).toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).appendSuper("BaseDto").append("code", code).append("name", name)
        .append("instituteType", instituteType).append("status", status).append("statusReason", statusReason)
        .append("statusUpdatedDate", statusUpdatedDate).append("createdDate", createdDate)
        .append("updatedDate", updatedDate).append("comment", comment).toString();
  }
}
