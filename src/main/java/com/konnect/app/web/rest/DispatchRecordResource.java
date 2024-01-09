package com.konnect.app.web.rest;

import com.konnect.app.repository.DispatchRecordRepository;
import com.konnect.app.service.DispatchRecordService;
import com.konnect.app.service.dto.DispatchRecordDTO;
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
 * REST controller for managing {@link com.konnect.app.domain.DispatchRecord}.
 */
@RestController
@RequestMapping("/api/dispatch-records")
public class DispatchRecordResource {

    private final Logger log = LoggerFactory.getLogger(DispatchRecordResource.class);

    private static final String ENTITY_NAME = "dispatchRecord";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DispatchRecordService dispatchRecordService;

    private final DispatchRecordRepository dispatchRecordRepository;

    public DispatchRecordResource(DispatchRecordService dispatchRecordService, DispatchRecordRepository dispatchRecordRepository) {
        this.dispatchRecordService = dispatchRecordService;
        this.dispatchRecordRepository = dispatchRecordRepository;
    }

    /**
     * {@code POST  /dispatch-records} : Create a new dispatchRecord.
     *
     * @param dispatchRecordDTO the dispatchRecordDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dispatchRecordDTO, or with status {@code 400 (Bad Request)} if the dispatchRecord has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DispatchRecordDTO> createDispatchRecord(@RequestBody DispatchRecordDTO dispatchRecordDTO)
        throws URISyntaxException {
        log.debug("REST request to save DispatchRecord : {}", dispatchRecordDTO);
        if (dispatchRecordDTO.getId() != null) {
            throw new BadRequestAlertException("A new dispatchRecord cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DispatchRecordDTO result = dispatchRecordService.save(dispatchRecordDTO);
        return ResponseEntity
            .created(new URI("/api/dispatch-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dispatch-records/:id} : Updates an existing dispatchRecord.
     *
     * @param id the id of the dispatchRecordDTO to save.
     * @param dispatchRecordDTO the dispatchRecordDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dispatchRecordDTO,
     * or with status {@code 400 (Bad Request)} if the dispatchRecordDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dispatchRecordDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DispatchRecordDTO> updateDispatchRecord(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DispatchRecordDTO dispatchRecordDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DispatchRecord : {}, {}", id, dispatchRecordDTO);
        if (dispatchRecordDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dispatchRecordDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dispatchRecordRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DispatchRecordDTO result = dispatchRecordService.update(dispatchRecordDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dispatchRecordDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /dispatch-records/:id} : Partial updates given fields of an existing dispatchRecord, field will ignore if it is null
     *
     * @param id the id of the dispatchRecordDTO to save.
     * @param dispatchRecordDTO the dispatchRecordDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dispatchRecordDTO,
     * or with status {@code 400 (Bad Request)} if the dispatchRecordDTO is not valid,
     * or with status {@code 404 (Not Found)} if the dispatchRecordDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the dispatchRecordDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DispatchRecordDTO> partialUpdateDispatchRecord(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DispatchRecordDTO dispatchRecordDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DispatchRecord partially : {}, {}", id, dispatchRecordDTO);
        if (dispatchRecordDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dispatchRecordDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dispatchRecordRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DispatchRecordDTO> result = dispatchRecordService.partialUpdate(dispatchRecordDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dispatchRecordDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /dispatch-records} : get all the dispatchRecords.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dispatchRecords in body.
     */
    @GetMapping("")
    public ResponseEntity<List<DispatchRecordDTO>> getAllDispatchRecords(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of DispatchRecords");
        Page<DispatchRecordDTO> page = dispatchRecordService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /dispatch-records/:id} : get the "id" dispatchRecord.
     *
     * @param id the id of the dispatchRecordDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dispatchRecordDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DispatchRecordDTO> getDispatchRecord(@PathVariable("id") Long id) {
        log.debug("REST request to get DispatchRecord : {}", id);
        Optional<DispatchRecordDTO> dispatchRecordDTO = dispatchRecordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dispatchRecordDTO);
    }

    /**
     * {@code DELETE  /dispatch-records/:id} : delete the "id" dispatchRecord.
     *
     * @param id the id of the dispatchRecordDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDispatchRecord(@PathVariable("id") Long id) {
        log.debug("REST request to delete DispatchRecord : {}", id);
        dispatchRecordService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
