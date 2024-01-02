package com.konnect.app.service.mapper;

import com.konnect.app.domain.Area;
import com.konnect.app.domain.Employee;
import com.konnect.app.service.dto.AreaDTO;
import com.konnect.app.service.dto.EmployeeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Area} and its DTO {@link AreaDTO}.
 */
@Mapper(componentModel = "spring")
public interface AreaMapper extends EntityMapper<AreaDTO, Area> {
    @Mapping(target = "employee", source = "employee", qualifiedByName = "employeeId")
    AreaDTO toDto(Area s);

    @Named("employeeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EmployeeDTO toDtoEmployeeId(Employee employee);
}
