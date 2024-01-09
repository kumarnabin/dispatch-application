package com.konnect.app.web.rest;

import com.konnect.app.repository.EmployeeAreaRepository;
import com.konnect.app.service.EmployeeAreaService;
import com.konnect.app.service.dto.EmployeeAreaDTO;
import com.konnect.app.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.konnect.app.domain.EmployeeArea}.
 */
@RestController
@RequestMapping("/api/employee-areas")
public class EmployeeAreaResource {

    private final Logger log = LoggerFactory.getLogger(EmployeeAreaResource.class);

    private static final String ENTITY_NAME = "employeeArea";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EmployeeAreaService employeeAreaService;

    private final EmployeeAreaRepository employeeAreaRepository;

    public EmployeeAreaResource(EmployeeAreaService employeeAreaService, EmployeeAreaRepository employeeAreaRepository) {
        this.employeeAreaService = employeeAreaService;
        this.employeeAreaRepository = employeeAreaRepository;
    }

    /**
     * {@code POST  /employee-areas} : Create a new employeeArea.
     *
     * @param employeeAreaDTO the employeeAreaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new employeeAreaDTO, or with status {@code 400 (Bad Request)} if the employeeArea has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<EmployeeAreaDTO> createEmployeeArea(@RequestBody EmployeeAreaDTO employeeAreaDTO) throws URISyntaxException {
        log.debug("REST request to save EmployeeArea : {}", employeeAreaDTO);
        if (employeeAreaDTO.getId() != null) {
            throw new BadRequestAlertException("A new employeeArea cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EmployeeAreaDTO result = employeeAreaService.save(employeeAreaDTO);
        return ResponseEntity
            .created(new URI("/api/employee-areas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /employee-areas/:id} : Updates an existing employeeArea.
     *
     * @param id the id of the employeeAreaDTO to save.
     * @param employeeAreaDTO the employeeAreaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated employeeAreaDTO,
     * or with status {@code 400 (Bad Request)} if the employeeAreaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the employeeAreaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeAreaDTO> updateEmployeeArea(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody EmployeeAreaDTO employeeAreaDTO
    ) throws URISyntaxException {
        log.debug("REST request to update EmployeeArea : {}, {}", id, employeeAreaDTO);
        if (employeeAreaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, employeeAreaDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!employeeAreaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        EmployeeAreaDTO result = employeeAreaService.update(employeeAreaDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, employeeAreaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /employee-areas/:id} : Partial updates given fields of an existing employeeArea, field will ignore if it is null
     *
     * @param id the id of the employeeAreaDTO to save.
     * @param employeeAreaDTO the employeeAreaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated employeeAreaDTO,
     * or with status {@code 400 (Bad Request)} if the employeeAreaDTO is not valid,
     * or with status {@code 404 (Not Found)} if the employeeAreaDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the employeeAreaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EmployeeAreaDTO> partialUpdateEmployeeArea(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody EmployeeAreaDTO employeeAreaDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update EmployeeArea partially : {}, {}", id, employeeAreaDTO);
        if (employeeAreaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, employeeAreaDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!employeeAreaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EmployeeAreaDTO> result = employeeAreaService.partialUpdate(employeeAreaDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, employeeAreaDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /employee-areas} : get all the employeeAreas.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of employeeAreas in body.
     */
    @GetMapping("")
    public ResponseEntity<List<EmployeeAreaDTO>> getAllEmployeeAreas(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of EmployeeAreas");
        Page<EmployeeAreaDTO> page = employeeAreaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /employee-areas/:id} : get the "id" employeeArea.
     *
     * @param id the id of the employeeAreaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the employeeAreaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeAreaDTO> getEmployeeArea(@PathVariable("id") Long id) {
        log.debug("REST request to get EmployeeArea : {}", id);
        Optional<EmployeeAreaDTO> employeeAreaDTO = employeeAreaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(employeeAreaDTO);
    }

    /**
     * {@code DELETE  /employee-areas/:id} : delete the "id" employeeArea.
     *
     * @param id the id of the employeeAreaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployeeArea(@PathVariable("id") Long id) {
        log.debug("REST request to delete EmployeeArea : {}", id);
        employeeAreaService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
