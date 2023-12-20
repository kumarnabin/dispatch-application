package com.konnect.app.service;

import com.konnect.app.domain.BranchCircuit;
import com.konnect.app.repository.BranchCircuitRepository;
import com.konnect.app.service.dto.BranchCircuitDTO;
import com.konnect.app.service.mapper.BranchCircuitMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.konnect.app.domain.BranchCircuit}.
 */
@Service
@Transactional
public class BranchCircuitService {

    private final Logger log = LoggerFactory.getLogger(BranchCircuitService.class);

    private final BranchCircuitRepository branchCircuitRepository;

    private final BranchCircuitMapper branchCircuitMapper;

    public BranchCircuitService(BranchCircuitRepository branchCircuitRepository, BranchCircuitMapper branchCircuitMapper) {
        this.branchCircuitRepository = branchCircuitRepository;
        this.branchCircuitMapper = branchCircuitMapper;
    }

    /**
     * Save a branchCircuit.
     *
     * @param branchCircuitDTO the entity to save.
     * @return the persisted entity.
     */
    public BranchCircuitDTO save(BranchCircuitDTO branchCircuitDTO) {
        log.debug("Request to save BranchCircuit : {}", branchCircuitDTO);
        BranchCircuit branchCircuit = branchCircuitMapper.toEntity(branchCircuitDTO);
        branchCircuit = branchCircuitRepository.save(branchCircuit);
        return branchCircuitMapper.toDto(branchCircuit);
    }

    /**
     * Update a branchCircuit.
     *
     * @param branchCircuitDTO the entity to save.
     * @return the persisted entity.
     */
    public BranchCircuitDTO update(BranchCircuitDTO branchCircuitDTO) {
        log.debug("Request to update BranchCircuit : {}", branchCircuitDTO);
        BranchCircuit branchCircuit = branchCircuitMapper.toEntity(branchCircuitDTO);
        branchCircuit = branchCircuitRepository.save(branchCircuit);
        return branchCircuitMapper.toDto(branchCircuit);
    }

    /**
     * Partially update a branchCircuit.
     *
     * @param branchCircuitDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BranchCircuitDTO> partialUpdate(BranchCircuitDTO branchCircuitDTO) {
        log.debug("Request to partially update BranchCircuit : {}", branchCircuitDTO);

        return branchCircuitRepository
            .findById(branchCircuitDTO.getId())
            .map(existingBranchCircuit -> {
                branchCircuitMapper.partialUpdate(existingBranchCircuit, branchCircuitDTO);

                return existingBranchCircuit;
            })
            .map(branchCircuitRepository::save)
            .map(branchCircuitMapper::toDto);
    }

    /**
     * Get all the branchCircuits.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BranchCircuitDTO> findAll() {
        log.debug("Request to get all BranchCircuits");
        return branchCircuitRepository.findAll().stream().map(branchCircuitMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the branchCircuits with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<BranchCircuitDTO> findAllWithEagerRelationships(Pageable pageable) {
        return branchCircuitRepository.findAllWithEagerRelationships(pageable).map(branchCircuitMapper::toDto);
    }

    /**
     * Get one branchCircuit by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BranchCircuitDTO> findOne(Long id) {
        log.debug("Request to get BranchCircuit : {}", id);
        return branchCircuitRepository.findOneWithEagerRelationships(id).map(branchCircuitMapper::toDto);
    }

    /**
     * Delete the branchCircuit by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete BranchCircuit : {}", id);
        branchCircuitRepository.deleteById(id);
    }
}
