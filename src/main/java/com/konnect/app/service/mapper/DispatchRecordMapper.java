package com.konnect.app.service.mapper;

import com.konnect.app.domain.Dispatch;
import com.konnect.app.domain.DispatchRecord;
import com.konnect.app.domain.Employee;
import com.konnect.app.service.dto.DispatchDTO;
import com.konnect.app.service.dto.DispatchRecordDTO;
import com.konnect.app.service.dto.EmployeeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DispatchRecord} and its DTO {@link DispatchRecordDTO}.
 */
@Mapper(componentModel = "spring")
public interface DispatchRecordMapper extends EntityMapper<DispatchRecordDTO, DispatchRecord> {
    @Mapping(target = "employee", source = "employee", qualifiedByName = "employeeId")
    @Mapping(target = "dispatch", source = "dispatch", qualifiedByName = "dispatchId")
    DispatchRecordDTO toDto(DispatchRecord s);

    @Named("employeeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EmployeeDTO toDtoEmployeeId(Employee employee);

    @Named("dispatchId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DispatchDTO toDtoDispatchId(Dispatch dispatch);
}
