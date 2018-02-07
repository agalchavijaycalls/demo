package com.example.demo.model.referencedata;

import com.asoft.ainstitute.api.model.BaseEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "time_zone", uniqueConstraints = {@UniqueConstraint(columnNames = {"code"})})
public class TimezoneEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @NotNull
  @Basic(optional = false)
  @Size(min = 5, max = 20)
  @Column(name = "code", nullable = false, length = 20)
  private String code;

  @NotNull
  @Basic(optional = false)
  @Size(min = 5, max = 200)
  @Column(name = "name", length = 200)
  private String name;

  @NotNull
  @Basic(optional = false)
  @Column(name = "offset_value")
  private int offsetValue;

  @Basic(optional = false)
  @NotNull
  @Column(name = "created_date", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdDate;

  @Size(max = 200)
  @Column(name = "comment", length = 200)
  private String comment;

  @PrePersist
  private void onCreate() {
    Date today = new Date();
    this.setCreatedDate(today);
  }

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

  public int getOffsetValue() {
    return offsetValue;
  }

  public void setOffsetValue(int offset) {
    this.offsetValue = offset;
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

  public void setComment(String description) {
    this.comment = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    TimezoneEntity that = (TimezoneEntity) o;

    return new EqualsBuilder().append(code, that.code).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(code).toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).appendSuper("BaseEntity").append("code", code).append("name", name)
        .append("offsetValue", offsetValue).append("createdDate", createdDate).append("comment", comment).toString();
  }
}
