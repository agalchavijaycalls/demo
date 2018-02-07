package com.example.demo.model.referencedata;

import com.asoft.ainstitute.api.constant.Types;
import com.asoft.ainstitute.api.model.BaseEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "payroll_component", uniqueConstraints = {@UniqueConstraint(columnNames = {"code"})})
public class PayrollComponentEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Basic(optional = false)
  @NotNull
  @Size(min = 2, max = 20)
  @Column(name = "code", nullable = false, length = 20)
  private String code;

  @Basic(optional = false)
  @NotNull
  @Size(min = 2, max = 50)
  @Column(name = "name", length = 50)
  private String name;

  @Basic(optional = false)
  @NotNull
  @Column(name = "contribution_type", nullable = false, length = 20)
  @Enumerated(EnumType.STRING)
  private Types.PayContributionType contributionType;

  @JoinColumn(name = "component_type_id", referencedColumnName = "id")
  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  private PayrollComponentTypeEntity payrollComponentTypeEntity;

  @Size(max = 200)
  @Column(name = "comment", length = 200)
  private String comment;

  @Basic(optional = false)
  @NotNull
  @Column(name = "created_date", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdDate;

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

  public PayrollComponentTypeEntity getPayrollComponentTypeEntity() {
    return payrollComponentTypeEntity;
  }

  public void setPayrollComponentTypeEntity(PayrollComponentTypeEntity payrollComponentTypeEntity) {
    this.payrollComponentTypeEntity = payrollComponentTypeEntity;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date dateCreated) {
    this.createdDate = dateCreated;
  }

  public Types.PayContributionType getContributionType() {
    return contributionType;
  }

  public void setContributionType(Types.PayContributionType contributionType) {
    this.contributionType = contributionType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    PayrollComponentEntity that = (PayrollComponentEntity) o;

    return new EqualsBuilder().append(code, that.code).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(code).toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).appendSuper("BaseEntity").append("code", code).append("name", name)
        .append("contributionType", contributionType).append("payrollComponentTypeEntity", payrollComponentTypeEntity)
        .append("comment", comment).append("createdDate", createdDate).toString();
  }
}
