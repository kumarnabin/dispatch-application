package com.konnect.app.service.mapper;

import com.konnect.app.domain.MasterCircuit;
import com.konnect.app.domain.ServiceProvider;
import com.konnect.app.service.dto.MasterCircuitDTO;
import com.konnect.app.service.dto.ServiceProviderDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link MasterCircuit} and its DTO {@link MasterCircuitDTO}.
 */
@Mapper(componentModel = "spring")
public interface MasterCircuitMapper extends EntityMapper<MasterCircuitDTO, MasterCircuit> {
    @Mapping(target = "serviceProviders", source = "serviceProviders", qualifiedByName = "serviceProviderIdSet")
    MasterCircuitDTO toDto(MasterCircuit s);

    @Mapping(target = "removeServiceProvider", ignore = true)
    MasterCircuit toEntity(MasterCircuitDTO masterCircuitDTO);

    @Named("serviceProviderId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ServiceProviderDTO toDtoServiceProviderId(ServiceProvider serviceProvider);

    @Named("serviceProviderIdSet")
    default Set<ServiceProviderDTO> toDtoServiceProviderIdSet(Set<ServiceProvider> serviceProvider) {
        return serviceProvider.stream().map(this::toDtoServiceProviderId).collect(Collectors.toSet());
    }
}
