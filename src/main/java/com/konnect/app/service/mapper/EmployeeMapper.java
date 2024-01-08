package com.konnect.app.service.mapper;

import com.konnect.app.domain.Employee;
import com.konnect.app.domain.User;
import com.konnect.app.service.dto.EmployeeDTO;
import com.konnect.app.service.dto.UserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Employee} and its DTO {@link EmployeeDTO}.
 */
@Mapper(componentModel = "spring")
public interface EmployeeMapper extends EntityMapper<EmployeeDTO, Employee> {
    @Mapping(target = "user", source = "user", qualifiedByName = "userId")
    EmployeeDTO toDto(Employee s);

    @Named("userId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserDTO toDtoUserId(User user);
}
