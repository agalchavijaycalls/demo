package com.example.demo.dto.user;

import com.asoft.ainstitute.api.constant.Status;
import com.asoft.ainstitute.api.dto.BaseDto;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class LoginInfo extends BaseDto {

  private static final long serialVersionUID = 1L;

  private String password;

  private String securityQuestion;

  private String securityAnswer;

  private Status.LoginStatus status;

  private String statusReason;

  private Date statusUpdatedDate;

  private Date createdDate;

  private Date lastLoginDate;

  private Date passwordUpdatedDate;

  private String comment;

  private UserInfo userInfo;

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getSecurityQuestion() {
    return securityQuestion;
  }

  public void setSecurityQuestion(String securityQuestion) {
    this.securityQuestion = securityQuestion;
  }

  public String getSecurityAnswer() {
    return securityAnswer;
  }

  public void setSecurityAnswer(String securityAnswer) {
    this.securityAnswer = securityAnswer;
  }

  public Status.LoginStatus getStatus() {
    return status;
  }

  public void setStatus(Status.LoginStatus status) {
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

  public Date getLastLoginDate() {
    return lastLoginDate;
  }

  public void setLastLoginDate(Date dateLastLogin) {
    this.lastLoginDate = dateLastLogin;
  }

  public Date getPasswordUpdatedDate() {
    return passwordUpdatedDate;
  }

  public void setPasswordUpdatedDate(Date datePasswordUpdated) {
    this.passwordUpdatedDate = datePasswordUpdated;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String remark) {
    this.comment = remark;
  }

  public UserInfo getUserInfo() {
    return userInfo;
  }

  public void setUserInfo(UserInfo userInfo) {
    this.userInfo = userInfo;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof LoginInfo)) {
      return false;
    }

    LoginInfo that = (LoginInfo) o;

    return new EqualsBuilder().append(userInfo.getUserName(), that.userInfo.getUserName()).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(userInfo.getUserName()).toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).appendSuper("BaseDto").append("userName", userInfo.getUserName())
        .append("password", password).append("securityQuestion", securityQuestion)
        .append("securityAnswer", securityAnswer).append("status", status).append("statusReason", statusReason)
        .append("statusUpdatedDate", statusUpdatedDate).append("createdDate", createdDate)
        .append("lastLoginDate", lastLoginDate).append("passwordUpdatedDate", passwordUpdatedDate)
        .append("comment", comment).append("userInfo", userInfo).toString();
  }
}
