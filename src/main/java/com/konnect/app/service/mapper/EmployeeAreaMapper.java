package com.konnect.app.service.mapper;

import com.konnect.app.domain.Employee;
import com.konnect.app.domain.EmployeeArea;
import com.konnect.app.service.dto.EmployeeAreaDTO;
import com.konnect.app.service.dto.EmployeeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EmployeeArea} and its DTO {@link EmployeeAreaDTO}.
 */
@Mapper(componentModel = "spring")
public interface EmployeeAreaMapper extends EntityMapper<EmployeeAreaDTO, EmployeeArea> {
    @Mapping(target = "employee", source = "employee", qualifiedByName = "employeeId")
    EmployeeAreaDTO toDto(EmployeeArea s);

    @Named("employeeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EmployeeDTO toDtoEmployeeId(Employee employee);
}
