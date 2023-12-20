package com.konnect.app.service.mapper;

import com.konnect.app.domain.Branch;
import com.konnect.app.domain.BranchCircuit;
import com.konnect.app.domain.MasterCircuit;
import com.konnect.app.service.dto.BranchCircuitDTO;
import com.konnect.app.service.dto.BranchDTO;
import com.konnect.app.service.dto.MasterCircuitDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BranchCircuit} and its DTO {@link BranchCircuitDTO}.
 */
@Mapper(componentModel = "spring")
public interface BranchCircuitMapper extends EntityMapper<BranchCircuitDTO, BranchCircuit> {
    @Mapping(target = "masterCircuit", source = "masterCircuit", qualifiedByName = "masterCircuitId")
    @Mapping(target = "branches", source = "branches", qualifiedByName = "branchIdSet")
    BranchCircuitDTO toDto(BranchCircuit s);

    @Mapping(target = "removeBranch", ignore = true)
    BranchCircuit toEntity(BranchCircuitDTO branchCircuitDTO);

    @Named("masterCircuitId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    MasterCircuitDTO toDtoMasterCircuitId(MasterCircuit masterCircuit);

    @Named("branchId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    BranchDTO toDtoBranchId(Branch branch);

    @Named("branchIdSet")
    default Set<BranchDTO> toDtoBranchIdSet(Set<Branch> branch) {
        return branch.stream().map(this::toDtoBranchId).collect(Collectors.toSet());
    }
}
