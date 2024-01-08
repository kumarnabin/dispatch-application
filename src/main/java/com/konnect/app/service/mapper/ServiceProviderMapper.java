package com.konnect.app.service.mapper;

import com.konnect.app.domain.ServiceProvider;
import com.konnect.app.service.dto.ServiceProviderDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ServiceProvider} and its DTO {@link ServiceProviderDTO}.
 */
@Mapper(componentModel = "spring")
public interface ServiceProviderMapper extends EntityMapper<ServiceProviderDTO, ServiceProvider> {}
