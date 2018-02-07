package com.example.demo.service.reference;

import com.asoft.ainstitute.api.dto.referencedata.*;
import com.asoft.ainstitute.api.exception.ServiceException;

import java.util.List;

public interface ReferenceDataService {
  Country create(Country country) throws ServiceException;

  Country update(Country country) throws ServiceException;

  void remove(Country country) throws ServiceException;

  List<Country> findByExample(Country country) throws ServiceException;

  List<Country> findAllCountries() throws ServiceException;

  CurrencyType create(CurrencyType currencyType) throws ServiceException;

  CurrencyType update(CurrencyType currencyType) throws ServiceException;

  void remove(CurrencyType currencyType) throws ServiceException;

  List<CurrencyType> findByExample(CurrencyType currencyType) throws ServiceException;

  List<CurrencyType> findAllCurrencyTypes() throws ServiceException;

  InstituteType create(InstituteType instituteType) throws ServiceException;

  InstituteType update(InstituteType instituteType) throws ServiceException;

  void remove(InstituteType instituteType) throws ServiceException;

  List<InstituteType> findByExample(InstituteType instituteType) throws ServiceException;

  List<InstituteType> findAllInstituteTypes() throws ServiceException;

  PayFrequency create(PayFrequency payFrequency) throws ServiceException;

  PayFrequency update(PayFrequency payFrequency) throws ServiceException;

  void remove(PayFrequency payFrequency) throws ServiceException;

  List<PayFrequency> findByExample(PayFrequency payFrequency) throws ServiceException;

  List<PayFrequency> findAllPayFrequencies() throws ServiceException;

  PayrollComponent create(PayrollComponent payrollComponent) throws ServiceException;

  PayrollComponent update(PayrollComponent payrollComponent) throws ServiceException;

  void remove(PayrollComponent payrollComponent) throws ServiceException;

  List<PayrollComponent> findByExample(PayrollComponent payrollComponent) throws ServiceException;

  List<PayrollComponent> findAllPayrollComponents() throws ServiceException;

  PayrollComponentType create(PayrollComponentType payrollComponentType) throws ServiceException;

  PayrollComponentType update(PayrollComponentType payrollComponentType) throws ServiceException;

  void remove(PayrollComponentType payrollComponentType) throws ServiceException;

  List<PayrollComponentType> findByExample(PayrollComponentType payrollComponentType) throws ServiceException;

  List<PayrollComponentType> findAllPayrollComponentTypes() throws ServiceException;

  Permission create(Permission permission) throws ServiceException;

  Permission update(Permission permission) throws ServiceException;

  void remove(Permission permission) throws ServiceException;

  List<Permission> findByExample(Permission permission) throws ServiceException;

  List<Permission> findAllPermissions() throws ServiceException;

  Province create(Province province) throws ServiceException;

  Province update(Province province) throws ServiceException;

  void remove(Province province) throws ServiceException;

  List<Province> findByExample(Province province) throws ServiceException;

  List<Province> findAllProvinces() throws ServiceException;

  Role create(Role role) throws ServiceException;

  Role update(Role role) throws ServiceException;

  void remove(Role role) throws ServiceException;

  List<Role> findByExample(Role role) throws ServiceException;

  List<Role> findAllRoles() throws ServiceException;

  RolePermission create(RolePermission rolePermission) throws ServiceException;

  RolePermission update(RolePermission rolePermission) throws ServiceException;

  void remove(RolePermission rolePermission) throws ServiceException;

  List<RolePermission> findByExample(RolePermission rolePermission) throws ServiceException;

  List<RolePermission> findAllRolePermissions() throws ServiceException;

  Timezone create(Timezone timezone) throws ServiceException;

  Timezone update(Timezone timezone) throws ServiceException;

  void remove(Timezone timezone) throws ServiceException;

  List<Timezone> findByExample(Timezone timezone) throws ServiceException;

  List<Timezone> findAllTimezones() throws ServiceException;
}
