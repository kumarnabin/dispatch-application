package com.konnect.app.service.mapper;

import com.konnect.app.domain.Area;
import com.konnect.app.domain.Olt;
import com.konnect.app.service.dto.AreaDTO;
import com.konnect.app.service.dto.OltDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Area} and its DTO {@link AreaDTO}.
 */
@Mapper(componentModel = "spring")
public interface AreaMapper extends EntityMapper<AreaDTO, Area> {
    @Mapping(target = "olt", source = "olt", qualifiedByName = "oltId")
    AreaDTO toDto(Area s);

    @Named("oltId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    OltDTO toDtoOltId(Olt olt);
}
