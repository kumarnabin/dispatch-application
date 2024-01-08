package com.konnect.app.service.mapper;

import com.konnect.app.domain.Attendance;
import com.konnect.app.domain.Employee;
import com.konnect.app.service.dto.AttendanceDTO;
import com.konnect.app.service.dto.EmployeeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Attendance} and its DTO {@link AttendanceDTO}.
 */
@Mapper(componentModel = "spring")
public interface AttendanceMapper extends EntityMapper<AttendanceDTO, Attendance> {
    @Mapping(target = "employee", source = "employee", qualifiedByName = "employeeId")
    AttendanceDTO toDto(Attendance s);

    @Named("employeeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EmployeeDTO toDtoEmployeeId(Employee employee);
}
