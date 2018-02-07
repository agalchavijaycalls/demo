package com.example.demo.model.referencedata;

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
@Table(name = "role", uniqueConstraints = {@UniqueConstraint(columnNames = {"code"})})
public class RoleEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Basic(optional = false)
  @NotNull
  @Size(min = 5, max = 20)
  @Column(name = "code", nullable = false, length = 20)
  private String code;

  @Basic(optional = false)
  @NotNull
  @Size(min = 5, max = 50)
  @Column(name = "name", length = 50)
  private String name;

  @Basic(optional = false)
  @NotNull
  @Column(name = "created_date", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date dateCreated;

  @Size(max = 200)
  @Column(name = "comment", length = 200)
  private String comment;

  @OneToMany(mappedBy = "roleEntity", fetch = FetchType.LAZY)
  private List<RolePermissionEntity> rolePermissionEntities;

  @PrePersist
  private void onCreate() {
    Date today = new Date();
    this.setDateCreated(today);
  }

  public String getCode() {
    return code;
  }

  public void setCode(String name) {
    this.code = name;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String description) {
    this.comment = description;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<RolePermissionEntity> getRolePermissionEntities() {
    return rolePermissionEntities;
  }

  public void setRolePermissionEntities(List<RolePermissionEntity> rolePermissionEntities) {
    this.rolePermissionEntities = rolePermissionEntities;
  }

  public Date getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(Date dateCreated) {
    this.dateCreated = dateCreated;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    RoleEntity that = (RoleEntity) o;

    return new EqualsBuilder().append(code, that.code).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(code).toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).appendSuper("BaseEntity").append("code", code).append("name", name)
        .append("dateCreated", dateCreated).append("comment", comment).toString();
  }
}
