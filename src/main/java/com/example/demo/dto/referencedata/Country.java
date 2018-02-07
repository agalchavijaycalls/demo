package com.example.demo.dto.referencedata;

import com.asoft.ainstitute.api.dto.BaseDto;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;
import java.util.List;

public class Country extends BaseDto {

  private static final long serialVersionUID = 1L;

  private String code;

  private String name;

  private String isoCode;

  private Integer numCode;

  private Date createdDate;

  private String comment;

  private List<Province> provinces;

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

  public String getIsoCode() {
    return isoCode;
  }

  public void setIsoCode(String isoCode) {
    this.isoCode = isoCode;
  }

  public Integer getNumCode() {
    return numCode;
  }

  public void setNumCode(Integer numCode) {
    this.numCode = numCode;
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

  public void setComment(String comment) {
    this.comment = comment;
  }

  public List<Province> getProvinces() {
    return provinces;
  }

  public void setProvinces(List<Province> provinces) {
    this.provinces = provinces;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Country that = (Country) o;

    return new EqualsBuilder().append(code, that.code).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(code).toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).appendSuper("BaseDto").append("code", code).append("name", name)
        .append("isoCode", isoCode).append("numCode", numCode).append("createdDate", createdDate)
        .append("comment", comment).toString();
  }
}
