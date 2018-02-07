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
@Table(name = "country", uniqueConstraints = {@UniqueConstraint(columnNames = {"code"})})
public class CountryEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Basic(optional = false)
  @NotNull
  @Size(min = 2, max = 5)
  @Column(name = "code", nullable = false, length = 5)
  private String code;

  @Basic(optional = false)
  @NotNull
  @Size(min = 2, max = 80)
  @Column(name = "name", nullable = false, length = 80)
  private String name;

  @Basic(optional = false)
  @NotNull
  @Size(min = 3, max = 5)
  @Column(name = "iso_code", nullable = false, length = 5)
  private String isoCode;

  @Basic(optional = false)
  @NotNull
  @Column(name = "num_code", nullable = false)
  private Integer numCode;

  @Basic(optional = false)
  @NotNull
  @Column(name = "created_date", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdDate;

  @Size(max = 200)
  @Column(name = "comment", length = 200)
  private String comment;

  @OneToMany(mappedBy = "countryEntity", fetch = FetchType.LAZY)
  private List<ProvinceEntity> provinceEntities;

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

  public List<ProvinceEntity> getProvinceEntities() {
    return provinceEntities;
  }

  public void setProvinceEntities(List<ProvinceEntity> provinceEntities) {
    this.provinceEntities = provinceEntities;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    CountryEntity that = (CountryEntity) o;

    return new EqualsBuilder().append(code, that.code).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(code).toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).appendSuper("BaseEntity").append("code", code).append("name", name)
        .append("isoCode", isoCode).append("numCode", numCode).append("createdDate", createdDate)
        .append("comment", comment).toString();
  }
}
