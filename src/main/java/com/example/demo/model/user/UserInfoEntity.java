package com.example.demo.model.user;

import com.asoft.ainstitute.api.model.BaseEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user_info",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"user_name"}), @UniqueConstraint(columnNames = {"email_id"}),
        @UniqueConstraint(columnNames = {"mobile_no"})})
public class UserInfoEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Basic(optional = false)
  @NotNull
  @Size(min = 8, max = 20)
  @Column(name = "user_name", nullable = false, length = 20)
  private String userName;

  @Basic(optional = false)
  @NotNull
  @Size(min = 8, max = 20)
  @Column(name = "display_name", nullable = false, length = 20)
  private String displayName;

  @Basic(optional = false)
  @NotNull
  @Size(min = 8, max = 255)
  @Column(name = "email_id", nullable = false, length = 255)
  private String emailId;

  @Basic(optional = false)
  @NotNull
  @Size(min = 8, max = 20)
  @Column(name = "mobile_no", nullable = false, length = 20)
  private String mobileNo;

  @Basic(optional = false)
  @NotNull
  @Column(name = "created_date", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdDate;

  @Basic(optional = false)
  @NotNull
  @Column(name = "updated_date", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date updatedDate;

  @Size(max = 200)
  @Column(name = "comment", length = 200)
  private String comment;

  @OneToMany(mappedBy = "userInfoEntity", fetch = FetchType.LAZY)
  private List<InstituteMemberEntity> instituteMemberEntities;

  @PrePersist
  private void onCreate() {
    Date today = new Date();
    this.setCreatedDate(today);
    this.setUpdatedDate(today);
  }

  @PreUpdate
  private void onUpdate() {
    this.setUpdatedDate(new Date());
  }

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

  public List<InstituteMemberEntity> getInstituteMemberEntities() {
    return instituteMemberEntities;
  }

  public void setInstituteMemberEntities(List<InstituteMemberEntity> instituteMemberEntities) {
    this.instituteMemberEntities = instituteMemberEntities;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    UserInfoEntity that = (UserInfoEntity) o;

    return new EqualsBuilder().append(userName, that.userName).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(userName).toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).appendSuper("BaseEntity").append("userName", userName)
        .append("displayName", displayName).append("emailId", emailId).append("mobileNo", mobileNo)
        .append("createdDate", createdDate).append("updatedDate", updatedDate).append("comment", comment).toString();
  }
}
