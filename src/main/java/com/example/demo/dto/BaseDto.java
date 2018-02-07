package com.example.demo.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public abstract class BaseDto implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long id;

  private Long version;

  public Long getId() {
    return id;
  }

  public Long getVersion() {
    return version;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("id", id).append("version", version).toString();
  }
}
