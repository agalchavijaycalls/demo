package com.example.demo.mapper;

import com.asoft.ainstitute.api.dto.referencedata.*;
import com.asoft.ainstitute.api.model.referencedata.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ReferenceDataEntityDtoMapper {

  void mapCountryToEntity(Country country, @MappingTarget CountryEntity countryEntity);

  void mapCurrencyTypeToEntity(CurrencyType currencyType, @MappingTarget CurrencyTypeEntity currencyTypeEntity);

  void mapInstituteTypeToEntity(InstituteType instituteType, @MappingTarget InstituteTypeEntity instituteTypeEntity);

  void mapPayFrequencyToEntity(PayFrequency payFrequency, @MappingTarget PayFrequencyEntity payFrequencyEntity);

  void mapPayrollComponentToEntity(PayrollComponent payrollComponent,
      @MappingTarget PayrollComponentEntity payrollComponentEntity);

  void mapPayrollComponentTypeToEntity(PayrollComponentType payrollComponentType,
      @MappingTarget PayrollComponentTypeEntity payrollComponentTypeEntity);

  void mapPermissionToEntity(Permission permission, @MappingTarget PermissionEntity permissionEntity);

  void mapProvinceToEntity(Province province, @MappingTarget ProvinceEntity provinceEntity);

  void mapRoleToEntity(Role role, @MappingTarget RoleEntity roleEntity);

  void mapRolePermissionToEntity(RolePermission rolePermission,
      @MappingTarget RolePermissionEntity rolePermissionEntity);

  void mapTimezoneToEntity(Timezone Timezone, @MappingTarget TimezoneEntity timezoneEntity);

  Country mapCountryToDto(CountryEntity countryEntity);

  @Named("WithProvince")
  @Mappings({@Mapping(source = "provinceEntities", target = "provinces")})
  Country mapCountryToDtoWithProvince(CountryEntity countryEntity);

  CurrencyType mapCurrencyTypeToDto(CurrencyTypeEntity currencyTypeEntity);

  InstituteType mapInstituteTypeToDto(InstituteTypeEntity instituteTypeEntity);

  PayFrequency mapPayFrequencyToDto(PayFrequencyEntity payFrequencyEntity);

  @Mappings({@Mapping(source = "payrollComponentTypeEntity", target = "payrollComponentType")})
  PayrollComponent mapPayrollComponentToDto(PayrollComponentEntity payrollComponentEntity);

  PayrollComponentType mapPayrollComponentTypeToDto(PayrollComponentTypeEntity payrollComponentTypeEntity);

  Permission mapPermissionToDto(PermissionEntity permissionEntity);

  Province mapProvinceToDto(ProvinceEntity provinceEntity);

  Role mapRoleToDto(RoleEntity roleEntity);

  @Named("WithPermissions")
  @Mappings({@Mapping(source = "rolePermissionEntities", target = "rolePermissions")})
  Role mapRoleToDtoWithPermission(RoleEntity roleEntity);

  @Mappings({@Mapping(source = "permissionEntity", target = "permission")})
  RolePermission mapRolePermissionToDto(RolePermissionEntity rolePermissionEntity);

  @Named("WithRoles")
  @Mappings(
      {@Mapping(source = "permissionEntity", target = "permission"), @Mapping(source = "roleEntity", target = "role")})
  RolePermission mapRolePermissionToDtoWithRoles(RolePermissionEntity rolePermissionEntity);

  Timezone mapTimezoneToDto(TimezoneEntity TimezoneEntity);
}
