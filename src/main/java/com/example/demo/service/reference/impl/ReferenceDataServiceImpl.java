package com.example.demo.service.reference.impl;

import com.asoft.ainstitute.api.dto.referencedata.*;
import com.asoft.ainstitute.api.exception.ServiceException;
import com.asoft.ainstitute.api.exception.handler.ServiceExceptionHandler;
import com.asoft.ainstitute.api.mapper.ReferenceDataEntityDtoMapper;
import com.asoft.ainstitute.api.model.referencedata.*;
import com.asoft.ainstitute.api.service.reference.ReferenceDataLocalService;
import com.asoft.ainstitute.api.service.reference.ReferenceDataService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReferenceDataServiceImpl implements ReferenceDataService {

  @Autowired
  private ReferenceDataLocalService referenceDataLocalService;
  @Autowired
  private ReferenceDataEntityDtoMapper referenceDataEntityDtoMapper;
  @Autowired
  private ServiceExceptionHandler serviceExceptionHandler;

  @Override
  @Transactional
  public Country create(Country country) throws ServiceException {
    try {
      CountryEntity countryEntity = new CountryEntity();
      referenceDataEntityDtoMapper.mapCountryToEntity(country, countryEntity);
      referenceDataLocalService.create(countryEntity);
      return referenceDataEntityDtoMapper.mapCountryToDto(countryEntity);
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
    return null;
  }

  @Override
  @Transactional
  public Country update(Country country) throws ServiceException {
    try {
      CountryEntity countryEntityToBeUpdated = referenceDataLocalService
          .findByKey(CountryEntity.class, country.getCode());
      if (countryEntityToBeUpdated != null) {
        referenceDataEntityDtoMapper.mapCountryToEntity(country, countryEntityToBeUpdated);
        CountryEntity updatedCountryEntity = referenceDataLocalService.update(countryEntityToBeUpdated);
        return referenceDataEntityDtoMapper.mapCountryToDto(updatedCountryEntity);
      } else {
        throw new EntityNotFoundException("CountryEntity not found for Code : " + country.getCode());
      }
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
    return null;
  }

  @Override
  @Transactional

  public void remove(Country country) throws ServiceException {
    try {
      referenceDataLocalService.remove(CountryEntity.class, country.getCode());
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
  }

  @Override
  @Transactional

  public List<Country> findByExample(Country country) throws ServiceException {
    List<Country> countries = new ArrayList<>();
    try {
      if (StringUtils.isNotBlank(country.getCode())) {
        CountryEntity countryByKey = referenceDataLocalService.findByKey(CountryEntity.class, country.getCode());
        countries.add(referenceDataEntityDtoMapper.mapCountryToDto(countryByKey));
      } else {
        CountryEntity countryEntityQueryObject = new CountryEntity();
        referenceDataEntityDtoMapper.mapCountryToEntity(country, countryEntityQueryObject);
        List<CountryEntity> allCountryByExample = referenceDataLocalService.findByExample(countryEntityQueryObject);
        allCountryByExample
            .forEach(countryEntity -> countries.add(referenceDataEntityDtoMapper.mapCountryToDto(countryEntity)));
      }
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
    return countries;
  }

  @Override
  @Transactional

  public List<Country> findAllCountries() throws ServiceException {
    List<Country> countries = new ArrayList<>();
    try {
      List<CountryEntity> countryEntities = referenceDataLocalService.findAll(CountryEntity.class);
      countryEntities.forEach(
          countryEntity -> countries.add(referenceDataEntityDtoMapper.mapCountryToDtoWithProvince(countryEntity)));
      return countries;
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
    return null;
  }

  @Override
  @Transactional

  public CurrencyType create(CurrencyType currencyType) throws ServiceException {
    try {
      CurrencyTypeEntity currencyTypeEntity = new CurrencyTypeEntity();
      referenceDataEntityDtoMapper.mapCurrencyTypeToEntity(currencyType, currencyTypeEntity);
      referenceDataLocalService.create(currencyTypeEntity);
      return referenceDataEntityDtoMapper.mapCurrencyTypeToDto(currencyTypeEntity);
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
    return null;
  }

  @Override
  @Transactional

  public CurrencyType update(CurrencyType currencyType) throws ServiceException {
    try {
      CurrencyTypeEntity currencyTypeEntityToBeUpdated = referenceDataLocalService
          .findByKey(CurrencyTypeEntity.class, currencyType.getCode());
      if (currencyTypeEntityToBeUpdated != null) {
        referenceDataEntityDtoMapper.mapCurrencyTypeToEntity(currencyType, currencyTypeEntityToBeUpdated);
        CurrencyTypeEntity updatedCurrencyTypeEntity = referenceDataLocalService.update(currencyTypeEntityToBeUpdated);
        return referenceDataEntityDtoMapper.mapCurrencyTypeToDto(updatedCurrencyTypeEntity);
      } else {
        throw new EntityNotFoundException("CurrencyTypeEntity not found for Code : " + currencyType.getCode());
      }
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
    return null;
  }

  @Override
  @Transactional

  public void remove(CurrencyType currencyType) throws ServiceException {
    try {
      referenceDataLocalService.remove(CurrencyTypeEntity.class, currencyType.getCode());
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
  }

  @Override
  @Transactional

  public List<CurrencyType> findByExample(CurrencyType currencyType) throws ServiceException {
    List<CurrencyType> currencyTypes = new ArrayList<>();
    try {
      if (StringUtils.isNotBlank(currencyType.getCode())) {
        CurrencyTypeEntity currencyTypeByKey = referenceDataLocalService
            .findByKey(CurrencyTypeEntity.class, currencyType.getCode());
        currencyTypes.add(referenceDataEntityDtoMapper.mapCurrencyTypeToDto(currencyTypeByKey));
      } else {
        CurrencyTypeEntity currencyTypeEntityQueryObject = new CurrencyTypeEntity();
        referenceDataEntityDtoMapper.mapCurrencyTypeToEntity(currencyType, currencyTypeEntityQueryObject);
        List<CurrencyTypeEntity> allCurrencyTypeByExample = referenceDataLocalService
            .findByExample(currencyTypeEntityQueryObject);
        allCurrencyTypeByExample.forEach(currencyTypeEntity -> currencyTypes
            .add(referenceDataEntityDtoMapper.mapCurrencyTypeToDto(currencyTypeEntity)));
      }
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
    return currencyTypes;
  }

  @Override
  @Transactional

  public List<CurrencyType> findAllCurrencyTypes() throws ServiceException {
    List<CurrencyType> currencyTypes = new ArrayList<>();
    try {
      List<CurrencyTypeEntity> currencyTypeEntities = referenceDataLocalService.findAll(CurrencyTypeEntity.class);
      currencyTypeEntities.forEach(currencyTypeEntity -> currencyTypes
          .add(referenceDataEntityDtoMapper.mapCurrencyTypeToDto(currencyTypeEntity)));
      return currencyTypes;
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
    return null;
  }

  @Override
  @Transactional

  public InstituteType create(InstituteType instituteType) throws ServiceException {
    try {
      InstituteTypeEntity instituteTypeEntity = new InstituteTypeEntity();
      referenceDataEntityDtoMapper.mapInstituteTypeToEntity(instituteType, instituteTypeEntity);
      referenceDataLocalService.create(instituteTypeEntity);
      return referenceDataEntityDtoMapper.mapInstituteTypeToDto(instituteTypeEntity);
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
    return null;
  }

  @Override
  @Transactional

  public InstituteType update(InstituteType instituteType) throws ServiceException {
    try {
      InstituteTypeEntity instituteTypeEntityToBeUpdated = referenceDataLocalService
          .findByKey(InstituteTypeEntity.class, instituteType.getCode());
      if (instituteTypeEntityToBeUpdated != null) {
        referenceDataEntityDtoMapper.mapInstituteTypeToEntity(instituteType, instituteTypeEntityToBeUpdated);
        InstituteTypeEntity updatedInstituteTypeEntity = referenceDataLocalService
            .update(instituteTypeEntityToBeUpdated);
        return referenceDataEntityDtoMapper.mapInstituteTypeToDto(updatedInstituteTypeEntity);
      } else {
        throw new EntityNotFoundException("InstituteTypeEntity not found for Code : " + instituteType.getCode());
      }
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
    return null;
  }

  @Override
  @Transactional

  public void remove(InstituteType instituteType) throws ServiceException {
    try {
      referenceDataLocalService.remove(InstituteTypeEntity.class, instituteType.getCode());
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
  }

  @Override
  @Transactional

  public List<InstituteType> findByExample(InstituteType instituteType) throws ServiceException {
    List<InstituteType> instituteTypes = new ArrayList<>();
    try {
      if (StringUtils.isNotBlank(instituteType.getCode())) {
        InstituteTypeEntity instituteTypeByKey = referenceDataLocalService
            .findByKey(InstituteTypeEntity.class, instituteType.getCode());
        instituteTypes.add(referenceDataEntityDtoMapper.mapInstituteTypeToDto(instituteTypeByKey));
      } else {
        InstituteTypeEntity instituteTypeEntityQueryObject = new InstituteTypeEntity();
        referenceDataEntityDtoMapper.mapInstituteTypeToEntity(instituteType, instituteTypeEntityQueryObject);
        List<InstituteTypeEntity> allInstituteTypeByExample = referenceDataLocalService
            .findByExample(instituteTypeEntityQueryObject);
        allInstituteTypeByExample.forEach(instituteTypeEntity -> instituteTypes
            .add(referenceDataEntityDtoMapper.mapInstituteTypeToDto(instituteTypeEntity)));
      }
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
    return instituteTypes;
  }

  @Override
  @Transactional

  public List<InstituteType> findAllInstituteTypes() throws ServiceException {
    List<InstituteType> instituteTypes = new ArrayList<>();
    try {
      List<InstituteTypeEntity> instituteTypeEntities = referenceDataLocalService.findAll(InstituteTypeEntity.class);
      instituteTypeEntities.forEach(instituteTypeEntity -> instituteTypes
          .add(referenceDataEntityDtoMapper.mapInstituteTypeToDto(instituteTypeEntity)));
      return instituteTypes;
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
    return null;
  }

  @Override
  @Transactional

  public PayFrequency create(PayFrequency payFrequency) throws ServiceException {
    try {
      PayFrequencyEntity payFrequencyEntity = new PayFrequencyEntity();
      referenceDataEntityDtoMapper.mapPayFrequencyToEntity(payFrequency, payFrequencyEntity);
      referenceDataLocalService.create(payFrequencyEntity);
      return referenceDataEntityDtoMapper.mapPayFrequencyToDto(payFrequencyEntity);
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
    return null;
  }

  @Override
  @Transactional

  public PayFrequency update(PayFrequency payFrequency) throws ServiceException {
    try {
      PayFrequencyEntity payFrequencyEntityToBeUpdated = referenceDataLocalService
          .findByKey(PayFrequencyEntity.class, payFrequency.getCode());
      if (payFrequencyEntityToBeUpdated != null) {
        referenceDataEntityDtoMapper.mapPayFrequencyToEntity(payFrequency, payFrequencyEntityToBeUpdated);
        PayFrequencyEntity updatedPayFrequencyEntity = referenceDataLocalService.update(payFrequencyEntityToBeUpdated);
        return referenceDataEntityDtoMapper.mapPayFrequencyToDto(updatedPayFrequencyEntity);
      } else {
        throw new EntityNotFoundException("PayFrequencyEntity not found for Code : " + payFrequency.getCode());
      }
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
    return null;
  }

  @Override
  @Transactional

  public void remove(PayFrequency payFrequency) throws ServiceException {
    try {
      referenceDataLocalService.remove(PayFrequencyEntity.class, payFrequency.getCode());
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
  }

  @Override
  @Transactional

  public List<PayFrequency> findByExample(PayFrequency payFrequency) throws ServiceException {
    List<PayFrequency> payFrequencies = new ArrayList<>();
    try {
      if (StringUtils.isNotBlank(payFrequency.getCode())) {
        PayFrequencyEntity payFrequencyByKey = referenceDataLocalService
            .findByKey(PayFrequencyEntity.class, payFrequency.getCode());
        payFrequencies.add(referenceDataEntityDtoMapper.mapPayFrequencyToDto(payFrequencyByKey));
      } else {
        PayFrequencyEntity payFrequencyEntityQueryObject = new PayFrequencyEntity();
        referenceDataEntityDtoMapper.mapPayFrequencyToEntity(payFrequency, payFrequencyEntityQueryObject);
        List<PayFrequencyEntity> allPayFrequencyByExample = referenceDataLocalService
            .findByExample(payFrequencyEntityQueryObject);
        allPayFrequencyByExample.forEach(payFrequencyEntity -> payFrequencies
            .add(referenceDataEntityDtoMapper.mapPayFrequencyToDto(payFrequencyEntity)));
      }
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
    return payFrequencies;
  }

  @Override
  @Transactional

  public List<PayFrequency> findAllPayFrequencies() throws ServiceException {
    List<PayFrequency> payFrequencies = new ArrayList<>();
    try {
      List<PayFrequencyEntity> payFrequencyEntities = referenceDataLocalService.findAll(PayFrequencyEntity.class);
      payFrequencyEntities.forEach(payFrequencyEntity -> payFrequencies
          .add(referenceDataEntityDtoMapper.mapPayFrequencyToDto(payFrequencyEntity)));
      return payFrequencies;
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
    return null;
  }

  @Override
  @Transactional

  public PayrollComponent create(PayrollComponent payrollComponent) throws ServiceException {
    try {
      PayrollComponentEntity payrollComponentEntity = new PayrollComponentEntity();
      referenceDataEntityDtoMapper.mapPayrollComponentToEntity(payrollComponent, payrollComponentEntity);

      PayrollComponentTypeEntity payrollComponentTypeEntity = referenceDataLocalService
          .findByKey(PayrollComponentTypeEntity.class, payrollComponent.getContributionType().getCode());
      payrollComponentEntity.setPayrollComponentTypeEntity(payrollComponentTypeEntity);

      referenceDataLocalService.create(payrollComponentEntity);
      return referenceDataEntityDtoMapper.mapPayrollComponentToDto(payrollComponentEntity);
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
    return null;
  }

  @Override
  @Transactional

  public PayrollComponent update(PayrollComponent payrollComponent) throws ServiceException {
    try {
      PayrollComponentEntity payrollComponentEntityToBeUpdated = referenceDataLocalService
          .findByKey(PayrollComponentEntity.class, payrollComponent.getCode());
      if (payrollComponentEntityToBeUpdated != null) {
        referenceDataEntityDtoMapper.mapPayrollComponentToEntity(payrollComponent, payrollComponentEntityToBeUpdated);
        PayrollComponentEntity updatedPayrollComponentEntity = referenceDataLocalService
            .update(payrollComponentEntityToBeUpdated);
        return referenceDataEntityDtoMapper.mapPayrollComponentToDto(updatedPayrollComponentEntity);
      } else {
        throw new EntityNotFoundException("PayrollComponentEntity not found for Code : " + payrollComponent.getCode());
      }
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
    return null;
  }

  @Override
  @Transactional

  public void remove(PayrollComponent payrollComponent) throws ServiceException {
    try {
      referenceDataLocalService.remove(PayrollComponentEntity.class, payrollComponent.getCode());
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
  }

  @Override
  @Transactional

  public List<PayrollComponent> findByExample(PayrollComponent payrollComponent) throws ServiceException {
    List<PayrollComponent> payrollComponents = new ArrayList<>();
    try {
      if (StringUtils.isNotBlank(payrollComponent.getCode())) {
        PayrollComponentEntity payrollComponentByKey = referenceDataLocalService
            .findByKey(PayrollComponentEntity.class, payrollComponent.getCode());
        payrollComponents.add(referenceDataEntityDtoMapper.mapPayrollComponentToDto(payrollComponentByKey));
      } else {
        PayrollComponentEntity payrollComponentEntityQueryObject = new PayrollComponentEntity();
        referenceDataEntityDtoMapper.mapPayrollComponentToEntity(payrollComponent, payrollComponentEntityQueryObject);
        List<PayrollComponentEntity> allPayrollComponentByExample = referenceDataLocalService
            .findByExample(payrollComponentEntityQueryObject);
        allPayrollComponentByExample.forEach(payrollComponentEntity -> payrollComponents
            .add(referenceDataEntityDtoMapper.mapPayrollComponentToDto(payrollComponentEntity)));
      }
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
    return payrollComponents;
  }

  @Override
  @Transactional

  public List<PayrollComponent> findAllPayrollComponents() throws ServiceException {
    List<PayrollComponent> payrollComponents = new ArrayList<>();
    try {
      List<PayrollComponentEntity> payrollComponentEntities = referenceDataLocalService
          .findAll(PayrollComponentEntity.class);
      payrollComponentEntities.forEach(payrollComponentEntity -> payrollComponents
          .add((referenceDataEntityDtoMapper.mapPayrollComponentToDto(payrollComponentEntity))));
      return payrollComponents;
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
    return null;
  }

  @Override
  @Transactional

  public PayrollComponentType create(PayrollComponentType payrollComponentType) throws ServiceException {
    try {
      PayrollComponentTypeEntity payrollComponentTypeEntity = new PayrollComponentTypeEntity();
      referenceDataEntityDtoMapper.mapPayrollComponentTypeToEntity(payrollComponentType, payrollComponentTypeEntity);
      referenceDataLocalService.create(payrollComponentTypeEntity);
      return referenceDataEntityDtoMapper.mapPayrollComponentTypeToDto(payrollComponentTypeEntity);
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
    return null;
  }

  @Override
  @Transactional

  public PayrollComponentType update(PayrollComponentType payrollComponentType) throws ServiceException {
    try {
      PayrollComponentTypeEntity payrollComponentTypeEntityToBeUpdated = referenceDataLocalService
          .findByKey(PayrollComponentTypeEntity.class, payrollComponentType.getCode());
      if (payrollComponentTypeEntityToBeUpdated != null) {
        referenceDataEntityDtoMapper
            .mapPayrollComponentTypeToEntity(payrollComponentType, payrollComponentTypeEntityToBeUpdated);
        PayrollComponentTypeEntity updatedPayrollComponentTypeEntity = referenceDataLocalService
            .update(payrollComponentTypeEntityToBeUpdated);
        return referenceDataEntityDtoMapper.mapPayrollComponentTypeToDto(updatedPayrollComponentTypeEntity);
      } else {
        throw new EntityNotFoundException(
            "PayrollComponentTypeEntity not found for Code : " + payrollComponentType.getCode());
      }
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
    return null;
  }

  @Override
  @Transactional

  public void remove(PayrollComponentType payrollComponentType) throws ServiceException {
    try {
      referenceDataLocalService.remove(PayrollComponentTypeEntity.class, payrollComponentType.getCode());
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
  }

  @Override
  @Transactional

  public List<PayrollComponentType> findByExample(PayrollComponentType payrollComponentType) throws ServiceException {
    List<PayrollComponentType> payrollComponentTypes = new ArrayList<>();
    try {
      if (StringUtils.isNotBlank(payrollComponentType.getCode())) {
        PayrollComponentTypeEntity payrollComponentTypeByKey = referenceDataLocalService
            .findByKey(PayrollComponentTypeEntity.class, payrollComponentType.getCode());
        payrollComponentTypes.add(referenceDataEntityDtoMapper.mapPayrollComponentTypeToDto(payrollComponentTypeByKey));
      } else {
        PayrollComponentTypeEntity payrollComponentTypeEntityQueryObject = new PayrollComponentTypeEntity();
        referenceDataEntityDtoMapper
            .mapPayrollComponentTypeToEntity(payrollComponentType, payrollComponentTypeEntityQueryObject);
        List<PayrollComponentTypeEntity> allPayrollComponentTypeByExample = referenceDataLocalService
            .findByExample(payrollComponentTypeEntityQueryObject);
        allPayrollComponentTypeByExample.forEach(payrollComponentTypeEntity -> payrollComponentTypes
            .add(referenceDataEntityDtoMapper.mapPayrollComponentTypeToDto(payrollComponentTypeEntity)));
      }
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
    return payrollComponentTypes;
  }

  @Override
  @Transactional

  public List<PayrollComponentType> findAllPayrollComponentTypes() throws ServiceException {
    List<PayrollComponentType> payrollComponentTypes = new ArrayList<>();
    try {
      List<PayrollComponentTypeEntity> payrollComponentTypeEntities = referenceDataLocalService
          .findAll(PayrollComponentTypeEntity.class);
      payrollComponentTypeEntities.forEach(payrollComponentTypeEntity -> payrollComponentTypes
          .add((referenceDataEntityDtoMapper.mapPayrollComponentTypeToDto(payrollComponentTypeEntity))));
      return payrollComponentTypes;
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
    return null;
  }

  @Override
  @Transactional

  public Permission create(Permission permission) throws ServiceException {
    try {
      PermissionEntity permissionEntity = new PermissionEntity();
      referenceDataEntityDtoMapper.mapPermissionToEntity(permission, permissionEntity);
      referenceDataLocalService.create(permissionEntity);
      return referenceDataEntityDtoMapper.mapPermissionToDto(permissionEntity);
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
    return null;
  }

  @Override
  @Transactional

  public Permission update(Permission permission) throws ServiceException {
    try {
      PermissionEntity permissionEntityToBeUpdated = referenceDataLocalService
          .findByKey(PermissionEntity.class, permission.getCode());
      if (permissionEntityToBeUpdated != null) {
        referenceDataEntityDtoMapper.mapPermissionToEntity(permission, permissionEntityToBeUpdated);
        PermissionEntity updatedPermissionEntity = referenceDataLocalService.update(permissionEntityToBeUpdated);
        return referenceDataEntityDtoMapper.mapPermissionToDto(updatedPermissionEntity);
      } else {
        throw new EntityNotFoundException("PermissionEntity not found for Code : " + permission.getCode());
      }
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
    return null;
  }

  @Override
  @Transactional

  public void remove(Permission permission) throws ServiceException {
    try {
      referenceDataLocalService.remove(PermissionEntity.class, permission.getCode());
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
  }

  @Override
  @Transactional

  public List<Permission> findByExample(Permission permission) throws ServiceException {
    List<Permission> permissions = new ArrayList<>();
    try {
      if (StringUtils.isNotBlank(permission.getCode())) {
        PermissionEntity permissionByKey = referenceDataLocalService
            .findByKey(PermissionEntity.class, permission.getCode());
        permissions.add(referenceDataEntityDtoMapper.mapPermissionToDto(permissionByKey));
      } else {
        PermissionEntity permissionEntityQueryObject = new PermissionEntity();
        referenceDataEntityDtoMapper.mapPermissionToEntity(permission, permissionEntityQueryObject);
        List<PermissionEntity> allPermissionByExample = referenceDataLocalService
            .findByExample(permissionEntityQueryObject);
        allPermissionByExample.forEach(
            permissionEntity -> permissions.add(referenceDataEntityDtoMapper.mapPermissionToDto(permissionEntity)));
      }
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
    return permissions;
  }

  @Override
  @Transactional

  public List<Permission> findAllPermissions() throws ServiceException {
    List<Permission> permissions = new ArrayList<>();
    try {
      List<PermissionEntity> permissionEntities = referenceDataLocalService.findAll(PermissionEntity.class);
      permissionEntities.forEach(
          permissionEntity -> permissions.add(referenceDataEntityDtoMapper.mapPermissionToDto(permissionEntity)));
      return permissions;
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
    return null;
  }

  @Override
  @Transactional

  public Province create(Province province) throws ServiceException {
    try {
      ProvinceEntity provinceEntity = new ProvinceEntity();
      referenceDataEntityDtoMapper.mapProvinceToEntity(province, provinceEntity);

      CountryEntity countryEntity = referenceDataLocalService
          .findByKey(CountryEntity.class, province.getCountry().getCode());
      provinceEntity.setCountryEntity(countryEntity);

      referenceDataLocalService.create(provinceEntity);
      return referenceDataEntityDtoMapper.mapProvinceToDto(provinceEntity);
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
    return null;
  }

  @Override
  @Transactional

  public Province update(Province province) throws ServiceException {
    try {
      ProvinceEntity provinceEntityToBeUpdated = referenceDataLocalService
          .findByKey(ProvinceEntity.class, province.getId());
      if (provinceEntityToBeUpdated != null) {
        referenceDataEntityDtoMapper.mapProvinceToEntity(province, provinceEntityToBeUpdated);
        ProvinceEntity updatedProvinceEntity = referenceDataLocalService.update(provinceEntityToBeUpdated);
        return referenceDataEntityDtoMapper.mapProvinceToDto(updatedProvinceEntity);
      } else {
        throw new EntityNotFoundException("ProvinceEntity not found for Code : " + province.getId());
      }
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
    return null;
  }

  @Override
  @Transactional

  public void remove(Province province) throws ServiceException {
    try {
      referenceDataLocalService.remove(ProvinceEntity.class, province.getId());
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
  }

  @Override
  @Transactional

  public List<Province> findByExample(Province province) throws ServiceException {
    List<Province> provinces = new ArrayList<>();
    try {
      if (province.getId() != null) {
        ProvinceEntity provinceByKey = referenceDataLocalService.findByKey(ProvinceEntity.class, province.getId());
        provinces.add(referenceDataEntityDtoMapper.mapProvinceToDto(provinceByKey));
      } else {
        ProvinceEntity provinceEntityQueryObject = new ProvinceEntity();
        referenceDataEntityDtoMapper.mapProvinceToEntity(province, provinceEntityQueryObject);
        List<ProvinceEntity> allProvinceByExample = referenceDataLocalService.findByExample(provinceEntityQueryObject);
        allProvinceByExample
            .forEach(provinceEntity -> provinces.add(referenceDataEntityDtoMapper.mapProvinceToDto(provinceEntity)));
      }
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
    return provinces;
  }

  @Override
  @Transactional

  public List<Province> findAllProvinces() throws ServiceException {
    List<Province> provinces = new ArrayList<>();
    try {
      List<ProvinceEntity> provinceEntities = referenceDataLocalService.findAll(ProvinceEntity.class);
      provinceEntities
          .forEach(provinceEntity -> provinces.add(referenceDataEntityDtoMapper.mapProvinceToDto(provinceEntity)));
      return provinces;
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
    return null;
  }

  @Override
  @Transactional

  public Role create(Role role) throws ServiceException {
    try {
      RoleEntity roleEntity = new RoleEntity();
      referenceDataEntityDtoMapper.mapRoleToEntity(role, roleEntity);
      referenceDataLocalService.create(roleEntity);
      return referenceDataEntityDtoMapper.mapRoleToDto(roleEntity);
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
    return null;
  }

  @Override
  @Transactional

  public Role update(Role role) throws ServiceException {
    try {
      RoleEntity roleEntityToBeUpdated = referenceDataLocalService.findByKey(RoleEntity.class, role.getCode());
      if (roleEntityToBeUpdated != null) {
        referenceDataEntityDtoMapper.mapRoleToEntity(role, roleEntityToBeUpdated);
        RoleEntity updatedRoleEntity = referenceDataLocalService.update(roleEntityToBeUpdated);
        return referenceDataEntityDtoMapper.mapRoleToDto(updatedRoleEntity);
      } else {
        throw new EntityNotFoundException("RoleEntity not found for Code : " + role.getCode());
      }
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
    return null;
  }

  @Override
  @Transactional

  public void remove(Role role) throws ServiceException {
    try {
      referenceDataLocalService.remove(RoleEntity.class, role.getCode());
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
  }

  @Override
  @Transactional

  public List<Role> findByExample(Role role) throws ServiceException {
    List<Role> roles = new ArrayList<>();
    try {
      if (StringUtils.isNotBlank(role.getCode())) {
        RoleEntity roleByKey = referenceDataLocalService.findByKey(RoleEntity.class, role.getCode());
        roles.add(referenceDataEntityDtoMapper.mapRoleToDto(roleByKey));
      } else {
        RoleEntity roleEntityQueryObject = new RoleEntity();
        referenceDataEntityDtoMapper.mapRoleToEntity(role, roleEntityQueryObject);
        List<RoleEntity> allRoleByExample = referenceDataLocalService.findByExample(roleEntityQueryObject);
        allRoleByExample.forEach(roleEntity -> roles.add(referenceDataEntityDtoMapper.mapRoleToDto(roleEntity)));
      }
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
    return roles;
  }

  @Override
  @Transactional

  public List<Role> findAllRoles() throws ServiceException {
    List<Role> roles = new ArrayList<>();
    try {
      List<RoleEntity> roleEntities = referenceDataLocalService.findAll(RoleEntity.class);
      roleEntities
          .forEach(roleEntity -> roles.add(referenceDataEntityDtoMapper.mapRoleToDtoWithPermission(roleEntity)));
      return roles;
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
    return null;
  }

  @Override
  @Transactional

  public RolePermission create(RolePermission rolePermission) throws ServiceException {
    try {
      RolePermissionEntity rolePermissionEntity = new RolePermissionEntity();
      referenceDataEntityDtoMapper.mapRolePermissionToEntity(rolePermission, rolePermissionEntity);

      RoleEntity roleEntity = referenceDataLocalService.findByKey(RoleEntity.class, rolePermission.getRole().getCode());
      rolePermissionEntity.setRoleEntity(roleEntity);
      PermissionEntity permissionEntity = referenceDataLocalService
          .findByKey(PermissionEntity.class, rolePermission.getPermission().getCode());
      rolePermissionEntity.setPermissionEntity(permissionEntity);

      referenceDataLocalService.create(rolePermissionEntity);
      return referenceDataEntityDtoMapper.mapRolePermissionToDto(rolePermissionEntity);
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
    return null;
  }

  @Override
  @Transactional

  public RolePermission update(RolePermission rolePermission) throws ServiceException {
    try {
      RolePermissionEntity rolePermissionEntityToBeUpdated = referenceDataLocalService
          .findByKey(RolePermissionEntity.class, rolePermission.getId());
      if (rolePermissionEntityToBeUpdated != null) {
        referenceDataEntityDtoMapper.mapRolePermissionToEntity(rolePermission, rolePermissionEntityToBeUpdated);
        RolePermissionEntity updatedRolePermissionEntity = referenceDataLocalService
            .update(rolePermissionEntityToBeUpdated);
        return referenceDataEntityDtoMapper.mapRolePermissionToDto(updatedRolePermissionEntity);
      } else {
        throw new EntityNotFoundException("RolePermissionEntity not found for Code : " + rolePermission.getId());
      }
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
    return null;
  }

  @Override
  @Transactional

  public void remove(RolePermission rolePermission) throws ServiceException {
    try {
      referenceDataLocalService.remove(RolePermissionEntity.class, rolePermission.getId());
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
  }

  @Override
  @Transactional

  public List<RolePermission> findByExample(RolePermission rolePermission) throws ServiceException {
    List<RolePermission> rolePermissions = new ArrayList<>();
    try {
      if (rolePermission.getId() != null) {
        RolePermissionEntity rolePermissionByKey = referenceDataLocalService
            .findByKey(RolePermissionEntity.class, rolePermission.getId());
        rolePermissions.add(referenceDataEntityDtoMapper.mapRolePermissionToDto(rolePermissionByKey));
      } else {
        RolePermissionEntity rolePermissionEntityQueryObject = new RolePermissionEntity();
        referenceDataEntityDtoMapper.mapRolePermissionToEntity(rolePermission, rolePermissionEntityQueryObject);
        List<RolePermissionEntity> allRolePermissionByExample = referenceDataLocalService
            .findByExample(rolePermissionEntityQueryObject);
        allRolePermissionByExample.forEach(rolePermissionEntity -> rolePermissions
            .add(referenceDataEntityDtoMapper.mapRolePermissionToDto(rolePermissionEntity)));
      }
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
    return rolePermissions;
  }

  @Override
  @Transactional

  public List<RolePermission> findAllRolePermissions() throws ServiceException {
    List<RolePermission> rolePermissions = new ArrayList<>();
    try {
      List<RolePermissionEntity> rolePermissionEntities = referenceDataLocalService.findAll(RolePermissionEntity.class);
      rolePermissionEntities.forEach(rolePermissionEntity -> rolePermissions
          .add(referenceDataEntityDtoMapper.mapRolePermissionToDtoWithRoles(rolePermissionEntity)));
      return rolePermissions;
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
    return null;
  }

  @Override
  @Transactional

  public Timezone create(Timezone timezone) throws ServiceException {
    try {
      TimezoneEntity timezoneEntity = new TimezoneEntity();
      referenceDataEntityDtoMapper.mapTimezoneToEntity(timezone, timezoneEntity);
      referenceDataLocalService.create(timezoneEntity);
      return referenceDataEntityDtoMapper.mapTimezoneToDto(timezoneEntity);
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
    return null;
  }

  @Override
  @Transactional

  public Timezone update(Timezone timezone) throws ServiceException {
    try {
      TimezoneEntity timezoneEntityToBeUpdated = referenceDataLocalService
          .findByKey(TimezoneEntity.class, timezone.getCode());
      if (timezoneEntityToBeUpdated != null) {
        referenceDataEntityDtoMapper.mapTimezoneToEntity(timezone, timezoneEntityToBeUpdated);
        TimezoneEntity updatedTimezoneEntity = referenceDataLocalService.update(timezoneEntityToBeUpdated);
        return referenceDataEntityDtoMapper.mapTimezoneToDto(updatedTimezoneEntity);
      } else {
        throw new EntityNotFoundException("TimezoneEntity not found for Code : " + timezone.getCode());
      }
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
    return null;
  }

  @Override
  @Transactional

  public void remove(Timezone timezone) throws ServiceException {
    try {
      referenceDataLocalService.remove(TimezoneEntity.class, timezone.getCode());
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
  }

  @Override
  @Transactional

  public List<Timezone> findByExample(Timezone timezone) throws ServiceException {
    List<Timezone> timezones = new ArrayList<>();
    try {
      if (StringUtils.isNotBlank(timezone.getCode())) {
        TimezoneEntity timezoneByKey = referenceDataLocalService.findByKey(TimezoneEntity.class, timezone.getCode());
        timezones.add(referenceDataEntityDtoMapper.mapTimezoneToDto(timezoneByKey));
      } else {
        TimezoneEntity timezoneEntityQueryObject = new TimezoneEntity();
        referenceDataEntityDtoMapper.mapTimezoneToEntity(timezone, timezoneEntityQueryObject);
        List<TimezoneEntity> allTimezoneByExample = referenceDataLocalService.findByExample(timezoneEntityQueryObject);
        allTimezoneByExample
            .forEach(timezoneEntity -> timezones.add(referenceDataEntityDtoMapper.mapTimezoneToDto(timezoneEntity)));
      }
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
    return timezones;
  }

  @Override
  @Transactional

  public List<Timezone> findAllTimezones() throws ServiceException {
    List<Timezone> timezones = new ArrayList<>();
    try {
      List<TimezoneEntity> timezoneEntities = referenceDataLocalService.findAll(TimezoneEntity.class);
      timezoneEntities
          .forEach(timezoneEntity -> timezones.add(referenceDataEntityDtoMapper.mapTimezoneToDto(timezoneEntity)));
      return timezones;
    } catch (Exception exc) {
      serviceExceptionHandler.handle(exc);
    }
    return null;
  }

}
