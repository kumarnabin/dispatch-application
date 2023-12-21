package com.konnect.app.web.rest;

import com.konnect.app.repository.ExcelDataRepository;
import com.konnect.app.service.ExcelDataService;
import com.konnect.app.service.dto.ExcelDataDTO;
import com.konnect.app.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.konnect.app.domain.ExcelData}.
 */
@RestController
@RequestMapping("/api/excel-data")
public class ExcelDataResource {

    private final Logger log = LoggerFactory.getLogger(ExcelDataResource.class);

    private static final String ENTITY_NAME = "excelData";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ExcelDataService excelDataService;

    private final ExcelDataRepository excelDataRepository;

    public ExcelDataResource(ExcelDataService excelDataService, ExcelDataRepository excelDataRepository) {
        this.excelDataService = excelDataService;
        this.excelDataRepository = excelDataRepository;
    }

    /**
     * {@code POST  /excel-data} : Create a new excelData.
     *
     * @param excelDataDTO the excelDataDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new excelDataDTO, or with status {@code 400 (Bad Request)} if the excelData has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ExcelDataDTO> createExcelData(@RequestBody ExcelDataDTO excelDataDTO) throws URISyntaxException {
        log.debug("REST request to save ExcelData : {}", excelDataDTO);
        if (excelDataDTO.getId() != null) {
            throw new BadRequestAlertException("A new excelData cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ExcelDataDTO result = excelDataService.save(excelDataDTO);
        return ResponseEntity
            .created(new URI("/api/excel-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /excel-data/:id} : Updates an existing excelData.
     *
     * @param id the id of the excelDataDTO to save.
     * @param excelDataDTO the excelDataDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated excelDataDTO,
     * or with status {@code 400 (Bad Request)} if the excelDataDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the excelDataDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ExcelDataDTO> updateExcelData(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ExcelDataDTO excelDataDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ExcelData : {}, {}", id, excelDataDTO);
        if (excelDataDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, excelDataDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!excelDataRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ExcelDataDTO result = excelDataService.update(excelDataDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, excelDataDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /excel-data/:id} : Partial updates given fields of an existing excelData, field will ignore if it is null
     *
     * @param id the id of the excelDataDTO to save.
     * @param excelDataDTO the excelDataDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated excelDataDTO,
     * or with status {@code 400 (Bad Request)} if the excelDataDTO is not valid,
     * or with status {@code 404 (Not Found)} if the excelDataDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the excelDataDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ExcelDataDTO> partialUpdateExcelData(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ExcelDataDTO excelDataDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ExcelData partially : {}, {}", id, excelDataDTO);
        if (excelDataDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, excelDataDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!excelDataRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ExcelDataDTO> result = excelDataService.partialUpdate(excelDataDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, excelDataDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /excel-data} : get all the excelData.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of excelData in body.
     */
    @GetMapping("")
    public List<ExcelDataDTO> getAllExcelData() {
        log.debug("REST request to get all ExcelData");
        return excelDataService.findAll();
    }

    /**
     * {@code GET  /excel-data/:id} : get the "id" excelData.
     *
     * @param id the id of the excelDataDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the excelDataDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ExcelDataDTO> getExcelData(@PathVariable("id") Long id) {
        log.debug("REST request to get ExcelData : {}", id);
        Optional<ExcelDataDTO> excelDataDTO = excelDataService.findOne(id);
        return ResponseUtil.wrapOrNotFound(excelDataDTO);
    }

    /**
     * {@code DELETE  /excel-data/:id} : delete the "id" excelData.
     *
     * @param id the id of the excelDataDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExcelData(@PathVariable("id") Long id) {
        log.debug("REST request to delete ExcelData : {}", id);
        excelDataService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
