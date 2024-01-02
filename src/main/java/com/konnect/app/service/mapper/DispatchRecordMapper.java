package com.konnect.app.service.mapper;

import com.konnect.app.domain.Area;
import com.konnect.app.domain.DispatchRecord;
import com.konnect.app.domain.Employee;
import com.konnect.app.service.dto.AreaDTO;
import com.konnect.app.service.dto.DispatchRecordDTO;
import com.konnect.app.service.dto.EmployeeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DispatchRecord} and its DTO {@link DispatchRecordDTO}.
 */
@Mapper(componentModel = "spring")
public interface DispatchRecordMapper extends EntityMapper<DispatchRecordDTO, DispatchRecord> {
    @Mapping(target = "employee", source = "employee", qualifiedByName = "employeeId")
    @Mapping(target = "area", source = "area", qualifiedByName = "areaId")
    DispatchRecordDTO toDto(DispatchRecord s);

    @Named("employeeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EmployeeDTO toDtoEmployeeId(Employee employee);

    @Named("areaId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AreaDTO toDtoAreaId(Area area);
}
