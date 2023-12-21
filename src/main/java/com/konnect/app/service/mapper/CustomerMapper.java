package com.konnect.app.service.mapper;

import com.konnect.app.domain.Customer;
import com.konnect.app.domain.Team;
import com.konnect.app.service.dto.CustomerDTO;
import com.konnect.app.service.dto.TeamDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Customer} and its DTO {@link CustomerDTO}.
 */
@Mapper(componentModel = "spring")
public interface CustomerMapper extends EntityMapper<CustomerDTO, Customer> {
    @Mapping(target = "team", source = "team", qualifiedByName = "teamId")
    CustomerDTO toDto(Customer s);

    @Named("teamId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TeamDTO toDtoTeamId(Team team);
}
