package com.konnect.app.service.mapper;

import com.konnect.app.domain.Branch;
import com.konnect.app.domain.Olt;
import com.konnect.app.service.dto.BranchDTO;
import com.konnect.app.service.dto.OltDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Olt} and its DTO {@link OltDTO}.
 */
@Mapper(componentModel = "spring")
public interface OltMapper extends EntityMapper<OltDTO, Olt> {
    @Mapping(target = "branch", source = "branch", qualifiedByName = "branchId")
    OltDTO toDto(Olt s);

    @Named("branchId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    BranchDTO toDtoBranchId(Branch branch);
}
