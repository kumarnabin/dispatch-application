package com.konnect.app.service.mapper;

import com.konnect.app.domain.Branch;
import com.konnect.app.domain.ServiceProvider;
import com.konnect.app.service.dto.BranchDTO;
import com.konnect.app.service.dto.ServiceProviderDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Branch} and its DTO {@link BranchDTO}.
 */
@Mapper(componentModel = "spring")
public interface BranchMapper extends EntityMapper<BranchDTO, Branch> {
    @Mapping(target = "serviceProvider", source = "serviceProvider", qualifiedByName = "serviceProviderId")
    BranchDTO toDto(Branch s);

    @Named("serviceProviderId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ServiceProviderDTO toDtoServiceProviderId(ServiceProvider serviceProvider);
}
