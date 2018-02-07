package com.example.demo.dto.referencedata;

import com.asoft.ainstitute.api.constant.Types;
import com.asoft.ainstitute.api.dto.BaseDto;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class PayrollComponent extends BaseDto {

  private static final long serialVersionUID = 1L;

  private String code;

  private String name;

  private Types.PayContributionType contributionType;

  private PayrollComponentType payrollComponentType;

  private String comment;

  private Date createdDate;

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

  public PayrollComponentType getPayrollComponentType() {
    return payrollComponentType;
  }

  public void setPayrollComponentType(PayrollComponentType payrollComponentType) {
    this.payrollComponentType = payrollComponentType;
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

    PayrollComponent that = (PayrollComponent) o;

    return new EqualsBuilder().append(code, that.code).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(code).toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).appendSuper("BaseDto").append("code", code).append("name", name)
        .append("contributionType", contributionType).append("payrollComponentType", payrollComponentType)
        .append("comment", comment).append("createdDate", createdDate).toString();
  }
}
