package com.example.demo.constant;

public class Types {
  public enum PayContributionType {
    EARNING("Earning"), DEDUCTION("DEDUCTION");

    private final String type;

    PayContributionType(String type) {
      this.type = type;
    }

    public String getCode() {
      return this.type;
    }

    public String toString() {
      return this.getCode();
    }
  }

}
