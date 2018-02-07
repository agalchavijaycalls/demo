package com.example.demo.constant;

public class Status {
  public enum LoginStatus {
    PENDING("PENDING"), ACTIVE("ACTIVE"), INACTIVE("INACTIVE");

    private final String code;

    LoginStatus(String code) {
      this.code = code;
    }

    public String getCode() {
      return this.code;
    }
  }

  public enum InstituteStatus {
    PENDING("PENDING"), ACTIVE("ACTIVE");

    private final String code;

    InstituteStatus(String code) {
      this.code = code;
    }

    public String getCode() {
      return this.code;
    }
  }

  public enum InstituteMemberStatus {
    PENDING("PENDING"), ACTIVE("ACTIVE");

    private final String code;

    InstituteMemberStatus(String code) {
      this.code = code;
    }

    public String getCode() {
      return this.code;
    }
  }

  public enum RolePermissionStatus {
    PENDING("PENDING"), ACTIVE("ACTIVE");
    private final String code;

    RolePermissionStatus(String code) {
      this.code = code;
    }

    public String getCode() {
      return this.code;
    }
  }

  public enum InstituteMemberRoleStatus {
    PENDING("PENDING"), ACTIVE("ACTIVE");
    private final String code;

    InstituteMemberRoleStatus(String code) {
      this.code = code;
    }

    public String getCode() {
      return this.code;
    }
  }

  public enum EmployeeStatus {
    PENDING("PENDING"), ACTIVE("ACTIVE");

    private final String code;

    EmployeeStatus(String code) {
      this.code = code;
    }

    public String getCode() {
      return this.code;
    }
  }

  public enum SalarySlipStatus {
    PENDING("PENDING"), ACTIVE("ACTIVE");

    private final String code;

    SalarySlipStatus(String code) {
      this.code = code;
    }

    public String getCode() {
      return this.code;
    }
  }

  public enum EmployeeSalaryStatus {
    PENDING("PENDING"), ACTIVE("ACTIVE"), INACTIVE("INACTIVE");

    private final String code;

    EmployeeSalaryStatus(String code) {
      this.code = code;
    }

    public String getCode() {
      return this.code;
    }
  }

}
