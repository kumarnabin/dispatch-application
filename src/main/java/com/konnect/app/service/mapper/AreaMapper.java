package com.konnect.app.service.mapper;

import com.konnect.app.domain.Area;
import com.konnect.app.service.dto.AreaDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Area} and its DTO {@link AreaDTO}.
 */
@Mapper(componentModel = "spring")
public interface AreaMapper extends EntityMapper<AreaDTO, Area> {}
