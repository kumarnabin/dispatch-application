package com.konnect.app.web.rest;

import com.konnect.app.repository.MasterCircuitRepository;
import com.konnect.app.service.MasterCircuitService;
import com.konnect.app.service.dto.MasterCircuitDTO;
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
 * REST controller for managing {@link com.konnect.app.domain.MasterCircuit}.
 */
@RestController
@RequestMapping("/api/master-circuits")
public class MasterCircuitResource {

    private final Logger log = LoggerFactory.getLogger(MasterCircuitResource.class);

    private static final String ENTITY_NAME = "masterCircuit";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MasterCircuitService masterCircuitService;

    private final MasterCircuitRepository masterCircuitRepository;

    public MasterCircuitResource(MasterCircuitService masterCircuitService, MasterCircuitRepository masterCircuitRepository) {
        this.masterCircuitService = masterCircuitService;
        this.masterCircuitRepository = masterCircuitRepository;
    }

    /**
     * {@code POST  /master-circuits} : Create a new masterCircuit.
     *
     * @param masterCircuitDTO the masterCircuitDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new masterCircuitDTO, or with status {@code 400 (Bad Request)} if the masterCircuit has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<MasterCircuitDTO> createMasterCircuit(@RequestBody MasterCircuitDTO masterCircuitDTO) throws URISyntaxException {
        log.debug("REST request to save MasterCircuit : {}", masterCircuitDTO);
        if (masterCircuitDTO.getId() != null) {
            throw new BadRequestAlertException("A new masterCircuit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MasterCircuitDTO result = masterCircuitService.save(masterCircuitDTO);
        return ResponseEntity
            .created(new URI("/api/master-circuits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /master-circuits/:id} : Updates an existing masterCircuit.
     *
     * @param id the id of the masterCircuitDTO to save.
     * @param masterCircuitDTO the masterCircuitDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated masterCircuitDTO,
     * or with status {@code 400 (Bad Request)} if the masterCircuitDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the masterCircuitDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<MasterCircuitDTO> updateMasterCircuit(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody MasterCircuitDTO masterCircuitDTO
    ) throws URISyntaxException {
        log.debug("REST request to update MasterCircuit : {}, {}", id, masterCircuitDTO);
        if (masterCircuitDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, masterCircuitDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!masterCircuitRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        MasterCircuitDTO result = masterCircuitService.update(masterCircuitDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, masterCircuitDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /master-circuits/:id} : Partial updates given fields of an existing masterCircuit, field will ignore if it is null
     *
     * @param id the id of the masterCircuitDTO to save.
     * @param masterCircuitDTO the masterCircuitDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated masterCircuitDTO,
     * or with status {@code 400 (Bad Request)} if the masterCircuitDTO is not valid,
     * or with status {@code 404 (Not Found)} if the masterCircuitDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the masterCircuitDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<MasterCircuitDTO> partialUpdateMasterCircuit(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody MasterCircuitDTO masterCircuitDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update MasterCircuit partially : {}, {}", id, masterCircuitDTO);
        if (masterCircuitDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, masterCircuitDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!masterCircuitRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<MasterCircuitDTO> result = masterCircuitService.partialUpdate(masterCircuitDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, masterCircuitDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /master-circuits} : get all the masterCircuits.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of masterCircuits in body.
     */
    @GetMapping("")
    public List<MasterCircuitDTO> getAllMasterCircuits(
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get all MasterCircuits");
        return masterCircuitService.findAll();
    }

    /**
     * {@code GET  /master-circuits/:id} : get the "id" masterCircuit.
     *
     * @param id the id of the masterCircuitDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the masterCircuitDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<MasterCircuitDTO> getMasterCircuit(@PathVariable("id") Long id) {
        log.debug("REST request to get MasterCircuit : {}", id);
        Optional<MasterCircuitDTO> masterCircuitDTO = masterCircuitService.findOne(id);
        return ResponseUtil.wrapOrNotFound(masterCircuitDTO);
    }

    /**
     * {@code DELETE  /master-circuits/:id} : delete the "id" masterCircuit.
     *
     * @param id the id of the masterCircuitDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMasterCircuit(@PathVariable("id") Long id) {
        log.debug("REST request to delete MasterCircuit : {}", id);
        masterCircuitService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
