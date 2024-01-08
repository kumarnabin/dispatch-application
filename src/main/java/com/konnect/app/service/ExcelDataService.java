package com.konnect.app.service;

import com.konnect.app.domain.ExcelData;
import com.konnect.app.repository.ExcelDataRepository;
import com.konnect.app.service.dto.ExcelDataDTO;
import com.konnect.app.service.mapper.ExcelDataMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.konnect.app.domain.ExcelData}.
 */
@Service
@Transactional
public class ExcelDataService {

    private final Logger log = LoggerFactory.getLogger(ExcelDataService.class);

    private final ExcelDataRepository excelDataRepository;

    private final ExcelDataMapper excelDataMapper;

    public ExcelDataService(ExcelDataRepository excelDataRepository, ExcelDataMapper excelDataMapper) {
        this.excelDataRepository = excelDataRepository;
        this.excelDataMapper = excelDataMapper;
    }

    /**
     * Save a excelData.
     *
     * @param excelDataDTO the entity to save.
     * @return the persisted entity.
     */
    public ExcelDataDTO save(ExcelDataDTO excelDataDTO) {
        log.debug("Request to save ExcelData : {}", excelDataDTO);
        ExcelData excelData = excelDataMapper.toEntity(excelDataDTO);
        excelData = excelDataRepository.save(excelData);
        return excelDataMapper.toDto(excelData);
    }

    /**
     * Update a excelData.
     *
     * @param excelDataDTO the entity to save.
     * @return the persisted entity.
     */
    public ExcelDataDTO update(ExcelDataDTO excelDataDTO) {
        log.debug("Request to update ExcelData : {}", excelDataDTO);
        ExcelData excelData = excelDataMapper.toEntity(excelDataDTO);
        excelData = excelDataRepository.save(excelData);
        return excelDataMapper.toDto(excelData);
    }

    /**
     * Partially update a excelData.
     *
     * @param excelDataDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ExcelDataDTO> partialUpdate(ExcelDataDTO excelDataDTO) {
        log.debug("Request to partially update ExcelData : {}", excelDataDTO);

        return excelDataRepository
            .findById(excelDataDTO.getId())
            .map(existingExcelData -> {
                excelDataMapper.partialUpdate(existingExcelData, excelDataDTO);

                return existingExcelData;
            })
            .map(excelDataRepository::save)
            .map(excelDataMapper::toDto);
    }

    /**
     * Get all the excelData.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ExcelDataDTO> findAll() {
        log.debug("Request to get all ExcelData");
        return excelDataRepository.findAll().stream().map(excelDataMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one excelData by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ExcelDataDTO> findOne(Long id) {
        log.debug("Request to get ExcelData : {}", id);
        return excelDataRepository.findById(id).map(excelDataMapper::toDto);
    }

    /**
     * Delete the excelData by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ExcelData : {}", id);
        excelDataRepository.deleteById(id);
    }
}
