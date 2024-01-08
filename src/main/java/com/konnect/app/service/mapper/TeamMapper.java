package com.konnect.app.service.mapper;

import com.konnect.app.domain.Team;
import com.konnect.app.service.dto.TeamDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Team} and its DTO {@link TeamDTO}.
 */
@Mapper(componentModel = "spring")
public interface TeamMapper extends EntityMapper<TeamDTO, Team> {}
