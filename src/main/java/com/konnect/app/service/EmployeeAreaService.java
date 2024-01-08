package com.konnect.app.service;

import com.konnect.app.domain.EmployeeArea;
import com.konnect.app.repository.EmployeeAreaRepository;
import com.konnect.app.service.dto.EmployeeAreaDTO;
import com.konnect.app.service.mapper.EmployeeAreaMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.konnect.app.domain.EmployeeArea}.
 */
@Service
@Transactional
public class EmployeeAreaService {

    private final Logger log = LoggerFactory.getLogger(EmployeeAreaService.class);

    private final EmployeeAreaRepository employeeAreaRepository;

    private final EmployeeAreaMapper employeeAreaMapper;

    public EmployeeAreaService(EmployeeAreaRepository employeeAreaRepository, EmployeeAreaMapper employeeAreaMapper) {
        this.employeeAreaRepository = employeeAreaRepository;
        this.employeeAreaMapper = employeeAreaMapper;
    }

    /**
     * Save a employeeArea.
     *
     * @param employeeAreaDTO the entity to save.
     * @return the persisted entity.
     */
    public EmployeeAreaDTO save(EmployeeAreaDTO employeeAreaDTO) {
        log.debug("Request to save EmployeeArea : {}", employeeAreaDTO);
        EmployeeArea employeeArea = employeeAreaMapper.toEntity(employeeAreaDTO);
        employeeArea = employeeAreaRepository.save(employeeArea);
        return employeeAreaMapper.toDto(employeeArea);
    }

    /**
     * Update a employeeArea.
     *
     * @param employeeAreaDTO the entity to save.
     * @return the persisted entity.
     */
    public EmployeeAreaDTO update(EmployeeAreaDTO employeeAreaDTO) {
        log.debug("Request to update EmployeeArea : {}", employeeAreaDTO);
        EmployeeArea employeeArea = employeeAreaMapper.toEntity(employeeAreaDTO);
        employeeArea = employeeAreaRepository.save(employeeArea);
        return employeeAreaMapper.toDto(employeeArea);
    }

    /**
     * Partially update a employeeArea.
     *
     * @param employeeAreaDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EmployeeAreaDTO> partialUpdate(EmployeeAreaDTO employeeAreaDTO) {
        log.debug("Request to partially update EmployeeArea : {}", employeeAreaDTO);

        return employeeAreaRepository
            .findById(employeeAreaDTO.getId())
            .map(existingEmployeeArea -> {
                employeeAreaMapper.partialUpdate(existingEmployeeArea, employeeAreaDTO);

                return existingEmployeeArea;
            })
            .map(employeeAreaRepository::save)
            .map(employeeAreaMapper::toDto);
    }

    /**
     * Get all the employeeAreas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<EmployeeAreaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EmployeeAreas");
        return employeeAreaRepository.findAll(pageable).map(employeeAreaMapper::toDto);
    }

    /**
     * Get one employeeArea by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EmployeeAreaDTO> findOne(Long id) {
        log.debug("Request to get EmployeeArea : {}", id);
        return employeeAreaRepository.findById(id).map(employeeAreaMapper::toDto);
    }

    /**
     * Delete the employeeArea by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete EmployeeArea : {}", id);
        employeeAreaRepository.deleteById(id);
    }
}
