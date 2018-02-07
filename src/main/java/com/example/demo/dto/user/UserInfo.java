package com.example.demo.dto.user;

import com.asoft.ainstitute.api.dto.BaseDto;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;
import java.util.List;

public class UserInfo extends BaseDto {

  private static final long serialVersionUID = 1L;

  private String userName;

  private String displayName;

  private String emailId;

  private String mobileNo;

  private Date createdDate;

  private Date updatedDate;

  private String comment;

  private List<InstituteMember> instituteMembers;

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getEmailId() {
    return emailId;
  }

  public void setEmailId(String emailId) {
    this.emailId = emailId;
  }

  public String getMobileNo() {
    return mobileNo;
  }

  public void setMobileNo(String mobileNo) {
    this.mobileNo = mobileNo;
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

  public List<InstituteMember> getInstituteMembers() {
    return instituteMembers;
  }

  public void setInstituteMembers(List<InstituteMember> instituteMembers) {
    this.instituteMembers = instituteMembers;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    UserInfo that = (UserInfo) o;

    return new EqualsBuilder().append(userName, that.userName).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(userName).toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).appendSuper("BaseDto").append("userName", userName)
        .append("displayName", displayName).append("emailId", emailId).append("mobileNo", mobileNo)
        .append("createdDate", createdDate).append("updatedDate", updatedDate).append("comment", comment).toString();
  }
}
