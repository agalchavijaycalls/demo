package com.example.demo.mapper;

import com.asoft.ainstitute.api.dto.institute.Employee;
import com.asoft.ainstitute.api.dto.institute.Institute;
import com.asoft.ainstitute.api.model.institute.EmployeeEntity;
import com.asoft.ainstitute.api.model.institute.InstituteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(uses = {ReferenceDataEntityDtoMapper.class, UserDataEntityDtoMapper.class}, componentModel = "spring")
public interface InstituteEntityDtoMapper {

  void mapEmployeeToEntity(Employee employee, @MappingTarget EmployeeEntity employeeEntity);

  void mapInstituteToEntity(Institute institute, @MappingTarget InstituteEntity instituteEntity);

  Employee mapEmployeeToDto(EmployeeEntity employeeEntity);

  @Mappings(
      {@Mapping(source = "instituteMemberEntity", target = "instituteMember", qualifiedByName = "WithUserInfoOnly")})
  Employee mapEmployeeToDtoWithUserInfo(EmployeeEntity employeeEntity);

  @Mappings({@Mapping(source = "instituteTypeEntity", target = "instituteType")})
  Institute mapInstituteToDto(InstituteEntity instituteEntity);

}
