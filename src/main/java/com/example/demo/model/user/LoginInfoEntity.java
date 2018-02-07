package com.example.demo.model.user;

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
@Table(name = "login_info")
public class LoginInfoEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Basic(optional = false)
  @NotNull
  @Size(min = 10, max = 255)
  @Column(name = "password", nullable = false, length = 255)
  private String password;

  @Basic(optional = false)
  @NotNull
  @Size(min = 10, max = 255)
  @Column(name = "security_question", nullable = false, length = 255)
  private String securityQuestion;

  @Basic(optional = false)
  @NotNull
  @Size(min = 2, max = 50)
  @Column(name = "security_answer", nullable = false, length = 50)
  private String securityAnswer;

  @Basic(optional = false)
  @NotNull
  @Column(name = "status", nullable = false, length = 20)
  @Enumerated(EnumType.STRING)
  private Status.LoginStatus status;

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

  @Column(name = "last_login_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date lastLoginDate;

  @Basic(optional = false)
  @NotNull
  @Column(name = "password_updated_date", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date passwordUpdatedDate;

  @Size(max = 200)
  @Column(name = "comment", length = 200)
  private String comment;

  @JoinColumn(name = "user_id", referencedColumnName = "id")
  @OneToOne(optional = false, fetch = FetchType.LAZY)
  private UserInfoEntity userInfoEntity;

  @PrePersist
  private void onCreate() {
    Date today = new Date();
    this.setCreatedDate(today);
    this.setPasswordUpdatedDate(today);
    this.setStatusUpdatedDate(today);
  }

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

  public UserInfoEntity getUserInfoEntity() {
    return userInfoEntity;
  }

  public void setUserInfoEntity(UserInfoEntity userInfoEntity) {
    this.userInfoEntity = userInfoEntity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof LoginInfoEntity)) {
      return false;
    }

    LoginInfoEntity that = (LoginInfoEntity) o;

    return new EqualsBuilder().append(userInfoEntity, that.userInfoEntity).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(userInfoEntity).toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).appendSuper("BaseEntity").append("userName", userInfoEntity.getUserName())
        .append("securityQuestion", securityQuestion).append("status", status).append("statusReason", statusReason)
        .append("statusUpdatedDate", statusUpdatedDate).append("createdDate", createdDate)
        .append("lastLoginDate", lastLoginDate).append("passwordUpdatedDate", passwordUpdatedDate)
        .append("comment", comment).toString();
  }
}
