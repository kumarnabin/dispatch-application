package com.konnect.app.service.mapper;

import com.konnect.app.domain.EmployeeArea;
import com.konnect.app.service.dto.EmployeeAreaDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EmployeeArea} and its DTO {@link EmployeeAreaDTO}.
 */
@Mapper(componentModel = "spring")
public interface EmployeeAreaMapper extends EntityMapper<EmployeeAreaDTO, EmployeeArea> {}
