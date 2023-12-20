package com.konnect.app.service;

import com.konnect.app.domain.MasterCircuit;
import com.konnect.app.repository.MasterCircuitRepository;
import com.konnect.app.service.dto.MasterCircuitDTO;
import com.konnect.app.service.mapper.MasterCircuitMapper;
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
 * Service Implementation for managing {@link com.konnect.app.domain.MasterCircuit}.
 */
@Service
@Transactional
public class MasterCircuitService {

    private final Logger log = LoggerFactory.getLogger(MasterCircuitService.class);

    private final MasterCircuitRepository masterCircuitRepository;

    private final MasterCircuitMapper masterCircuitMapper;

    public MasterCircuitService(MasterCircuitRepository masterCircuitRepository, MasterCircuitMapper masterCircuitMapper) {
        this.masterCircuitRepository = masterCircuitRepository;
        this.masterCircuitMapper = masterCircuitMapper;
    }

    /**
     * Save a masterCircuit.
     *
     * @param masterCircuitDTO the entity to save.
     * @return the persisted entity.
     */
    public MasterCircuitDTO save(MasterCircuitDTO masterCircuitDTO) {
        log.debug("Request to save MasterCircuit : {}", masterCircuitDTO);
        MasterCircuit masterCircuit = masterCircuitMapper.toEntity(masterCircuitDTO);
        masterCircuit = masterCircuitRepository.save(masterCircuit);
        return masterCircuitMapper.toDto(masterCircuit);
    }

    /**
     * Update a masterCircuit.
     *
     * @param masterCircuitDTO the entity to save.
     * @return the persisted entity.
     */
    public MasterCircuitDTO update(MasterCircuitDTO masterCircuitDTO) {
        log.debug("Request to update MasterCircuit : {}", masterCircuitDTO);
        MasterCircuit masterCircuit = masterCircuitMapper.toEntity(masterCircuitDTO);
        masterCircuit = masterCircuitRepository.save(masterCircuit);
        return masterCircuitMapper.toDto(masterCircuit);
    }

    /**
     * Partially update a masterCircuit.
     *
     * @param masterCircuitDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<MasterCircuitDTO> partialUpdate(MasterCircuitDTO masterCircuitDTO) {
        log.debug("Request to partially update MasterCircuit : {}", masterCircuitDTO);

        return masterCircuitRepository
            .findById(masterCircuitDTO.getId())
            .map(existingMasterCircuit -> {
                masterCircuitMapper.partialUpdate(existingMasterCircuit, masterCircuitDTO);

                return existingMasterCircuit;
            })
            .map(masterCircuitRepository::save)
            .map(masterCircuitMapper::toDto);
    }

    /**
     * Get all the masterCircuits.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<MasterCircuitDTO> findAll() {
        log.debug("Request to get all MasterCircuits");
        return masterCircuitRepository.findAll().stream().map(masterCircuitMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the masterCircuits with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<MasterCircuitDTO> findAllWithEagerRelationships(Pageable pageable) {
        return masterCircuitRepository.findAllWithEagerRelationships(pageable).map(masterCircuitMapper::toDto);
    }

    /**
     * Get one masterCircuit by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MasterCircuitDTO> findOne(Long id) {
        log.debug("Request to get MasterCircuit : {}", id);
        return masterCircuitRepository.findOneWithEagerRelationships(id).map(masterCircuitMapper::toDto);
    }

    /**
     * Delete the masterCircuit by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MasterCircuit : {}", id);
        masterCircuitRepository.deleteById(id);
    }
}
