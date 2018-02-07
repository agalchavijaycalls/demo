package com.example.demo.model.user;

import com.asoft.ainstitute.api.constant.Status;
import com.asoft.ainstitute.api.model.BaseEntity;
import com.asoft.ainstitute.api.model.institute.InstituteEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "institute_member", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "institute_id"})})
public class InstituteMemberEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @JoinColumn(name = "user_id", referencedColumnName = "id")
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private UserInfoEntity userInfoEntity;

  @JoinColumn(name = "institute_id", referencedColumnName = "id")
  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  private InstituteEntity instituteEntity;

  @Basic(optional = false)
  @NotNull
  @Column(name = "status", nullable = false, length = 20)
  @Enumerated(EnumType.STRING)
  private Status.InstituteMemberStatus status;

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

  @OneToMany(mappedBy = "instituteMemberEntity", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
  private List<InstituteMemberRoleEntity> instituteMemberRoleEntities;

  @PrePersist
  private void onCreate() {
    Date today = new Date();
    this.setCreatedDate(today);
    this.setStatusUpdatedDate(today);
  }

  public UserInfoEntity getUserInfoEntity() {
    return userInfoEntity;
  }

  public void setUserInfoEntity(UserInfoEntity userInfoEntity) {
    this.userInfoEntity = userInfoEntity;
  }

  public InstituteEntity getInstituteEntity() {
    return instituteEntity;
  }

  public void setInstituteEntity(InstituteEntity instituteEntity) {
    this.instituteEntity = instituteEntity;
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

  public List<InstituteMemberRoleEntity> getInstituteMemberRoleEntities() {
    return instituteMemberRoleEntities;
  }

  public void setInstituteMemberRoleEntities(List<InstituteMemberRoleEntity> instituteMemberRoleEntities) {
    this.instituteMemberRoleEntities = instituteMemberRoleEntities;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    InstituteMemberEntity that = (InstituteMemberEntity) o;

    return new EqualsBuilder().append(userInfoEntity, that.userInfoEntity).append(instituteEntity, that.instituteEntity)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(userInfoEntity).append(instituteEntity).toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).appendSuper("BaseEntity").append("userInfoEntity", userInfoEntity)
        .append("instituteEntity", instituteEntity).append("status", status).append("statusReason", statusReason)
        .append("statusUpdatedDate", statusUpdatedDate).append("createdDate", createdDate).append("comment", comment)
        .toString();
  }
}
