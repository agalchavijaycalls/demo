package com.example.demo.dto.referencedata;

import com.asoft.ainstitute.api.dto.BaseDto;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;
import java.util.List;

public class Role extends BaseDto {

  private static final long serialVersionUID = 1L;

  private String code;

  private String name;

  private Date dateCreated;

  private String comment;

  private List<RolePermission> rolePermissions;

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

  public List<RolePermission> getRolePermissions() {
    return rolePermissions;
  }

  public void setRolePermissions(List<RolePermission> rolePermissions) {
    this.rolePermissions = rolePermissions;
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

    Role that = (Role) o;

    return new EqualsBuilder().append(code, that.code).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(code).toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).appendSuper("BaseDto").append("code", code).append("name", name)
        .append("dateCreated", dateCreated).append("comment", comment).toString();
  }
}
