package com.konnect.app.service.mapper;

import com.konnect.app.domain.Document;
import com.konnect.app.domain.Employee;
import com.konnect.app.service.dto.DocumentDTO;
import com.konnect.app.service.dto.EmployeeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Document} and its DTO {@link DocumentDTO}.
 */
@Mapper(componentModel = "spring")
public interface DocumentMapper extends EntityMapper<DocumentDTO, Document> {
    @Mapping(target = "employee", source = "employee", qualifiedByName = "employeeId")
    DocumentDTO toDto(Document s);

    @Named("employeeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EmployeeDTO toDtoEmployeeId(Employee employee);
}
