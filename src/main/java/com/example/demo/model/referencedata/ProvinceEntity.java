package com.example.demo.model.referencedata;

import com.asoft.ainstitute.api.model.BaseEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "province", uniqueConstraints = {@UniqueConstraint(columnNames = {"code", "country_id"})})
public class ProvinceEntity extends BaseEntity {
  private static final long serialVersionUID = 1L;

  @Basic(optional = false)
  @NotNull
  @Size(min = 2, max = 5)
  @Column(name = "code", nullable = false, length = 5)
  private String code;

  @Basic(optional = false)
  @NotNull
  @Size(min = 2, max = 50)
  @Column(name = "name", nullable = false, length = 50)
  private String name;

  @JoinColumn(name = "country_id", referencedColumnName = "id")
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private CountryEntity countryEntity;

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

  public CountryEntity getCountryEntity() {
    return countryEntity;
  }

  public void setCountryEntity(CountryEntity countryEntity) {
    this.countryEntity = countryEntity;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ProvinceEntity that = (ProvinceEntity) o;

    return new EqualsBuilder().append(code, that.code).append(countryEntity, that.countryEntity).isEquals();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).appendSuper("BaseEntity").append("code", code).append("name", name)
        .append("countryEntity", countryEntity).append("createdDate", createdDate).append("comment", comment)
        .toString();
  }
}
