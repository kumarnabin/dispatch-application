package com.konnect.app.web.rest;

import com.konnect.app.domain.Dispatch;
import com.konnect.app.repository.DispatchRepository;
import com.konnect.app.service.DispatchService;
import com.konnect.app.service.dto.DispatchDTO;
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
 * REST controller for managing {@link com.konnect.app.domain.Dispatch}.
 */
@RestController
@RequestMapping("/api/dispatches")
public class DispatchResource {

    private final Logger log = LoggerFactory.getLogger(DispatchResource.class);

    private static final String ENTITY_NAME = "dispatch";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DispatchService dispatchService;

    private final DispatchRepository dispatchRepository;

    public DispatchResource(DispatchService dispatchService, DispatchRepository dispatchRepository) {
        this.dispatchService = dispatchService;
        this.dispatchRepository = dispatchRepository;
    }

    /**
     * {@code POST  /dispatches} : Create a new dispatch.
     *
     * @param dispatchDTO the dispatchDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dispatchDTO, or with status {@code 400 (Bad Request)} if the dispatch has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DispatchDTO> createDispatch(@RequestBody DispatchDTO dispatchDTO) throws URISyntaxException {
        log.debug("REST request to save Dispatch : {}", dispatchDTO);
        if (dispatchDTO.getId() != null) {
            throw new BadRequestAlertException("A new dispatch cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DispatchDTO result = dispatchService.save(dispatchDTO);
        return ResponseEntity
            .created(new URI("/api/dispatches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dispatches/:id} : Updates an existing dispatch.
     *
     * @param id the id of the dispatchDTO to save.
     * @param dispatchDTO the dispatchDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dispatchDTO,
     * or with status {@code 400 (Bad Request)} if the dispatchDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dispatchDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DispatchDTO> updateDispatch(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DispatchDTO dispatchDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Dispatch : {}, {}", id, dispatchDTO);
        if (dispatchDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dispatchDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dispatchRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DispatchDTO result = dispatchService.update(dispatchDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, dispatchDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /dispatches/:id} : Partial updates given fields of an existing dispatch, field will ignore if it is null
     *
     * @param id the id of the dispatchDTO to save.
     * @param dispatchDTO the dispatchDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dispatchDTO,
     * or with status {@code 400 (Bad Request)} if the dispatchDTO is not valid,
     * or with status {@code 404 (Not Found)} if the dispatchDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the dispatchDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DispatchDTO> partialUpdateDispatch(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DispatchDTO dispatchDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Dispatch partially : {}, {}", id, dispatchDTO);
        if (dispatchDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dispatchDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dispatchRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DispatchDTO> result = dispatchService.partialUpdate(dispatchDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, dispatchDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /dispatches} : get all the dispatches.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dispatches in body.
     */
    @GetMapping("")
    public ResponseEntity<List<Dispatch>> getAllDispatches(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Dispatches");
        Page<Dispatch> page = dispatchService.findFilteredData(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /dispatches/:id} : get the "id" dispatch.
     *
     * @param id the id of the dispatchDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dispatchDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DispatchDTO> getDispatch(@PathVariable("id") Long id) {
        log.debug("REST request to get Dispatch : {}", id);
        Optional<DispatchDTO> dispatchDTO = dispatchService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dispatchDTO);
    }

    /**
     * {@code DELETE  /dispatches/:id} : delete the "id" dispatch.
     *
     * @param id the id of the dispatchDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDispatch(@PathVariable("id") Long id) {
        log.debug("REST request to delete Dispatch : {}", id);
        dispatchService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
