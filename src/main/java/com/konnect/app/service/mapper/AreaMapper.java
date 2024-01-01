package com.konnect.app.service.mapper;

import com.konnect.app.domain.Area;
import com.konnect.app.domain.Employee;
import com.konnect.app.service.dto.AreaDTO;
import com.konnect.app.service.dto.EmployeeDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Area} and its DTO {@link AreaDTO}.
 */
@Mapper(componentModel = "spring")
public interface AreaMapper extends EntityMapper<AreaDTO, Area> {
    @Mapping(target = "employees", source = "employees", qualifiedByName = "employeeIdSet")
    AreaDTO toDto(Area s);

    @Mapping(target = "removeEmployee", ignore = true)
    Area toEntity(AreaDTO areaDTO);

    @Named("employeeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EmployeeDTO toDtoEmployeeId(Employee employee);

    @Named("employeeIdSet")
    default Set<EmployeeDTO> toDtoEmployeeIdSet(Set<Employee> employee) {
        return employee.stream().map(this::toDtoEmployeeId).collect(Collectors.toSet());
    }
}
