package com.konnect.app.service.mapper;

import com.konnect.app.domain.Dispatch;
import com.konnect.app.domain.Team;
import com.konnect.app.service.dto.DispatchDTO;
import com.konnect.app.service.dto.TeamDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Dispatch} and its DTO {@link DispatchDTO}.
 */
@Mapper(componentModel = "spring")
public interface DispatchMapper extends EntityMapper<DispatchDTO, Dispatch> {
    @Mapping(target = "team", source = "team", qualifiedByName = "teamId")
    DispatchDTO toDto(Dispatch s);

    @Named("teamId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TeamDTO toDtoTeamId(Team team);
}
