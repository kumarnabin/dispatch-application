package com.konnect.app.web.rest;

import com.konnect.app.repository.BranchCircuitRepository;
import com.konnect.app.service.BranchCircuitService;
import com.konnect.app.service.dto.BranchCircuitDTO;
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
 * REST controller for managing {@link com.konnect.app.domain.BranchCircuit}.
 */
@RestController
@RequestMapping("/api/branch-circuits")
public class BranchCircuitResource {

    private final Logger log = LoggerFactory.getLogger(BranchCircuitResource.class);

    private static final String ENTITY_NAME = "branchCircuit";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BranchCircuitService branchCircuitService;

    private final BranchCircuitRepository branchCircuitRepository;

    public BranchCircuitResource(BranchCircuitService branchCircuitService, BranchCircuitRepository branchCircuitRepository) {
        this.branchCircuitService = branchCircuitService;
        this.branchCircuitRepository = branchCircuitRepository;
    }

    /**
     * {@code POST  /branch-circuits} : Create a new branchCircuit.
     *
     * @param branchCircuitDTO the branchCircuitDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new branchCircuitDTO, or with status {@code 400 (Bad Request)} if the branchCircuit has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<BranchCircuitDTO> createBranchCircuit(@RequestBody BranchCircuitDTO branchCircuitDTO) throws URISyntaxException {
        log.debug("REST request to save BranchCircuit : {}", branchCircuitDTO);
        if (branchCircuitDTO.getId() != null) {
            throw new BadRequestAlertException("A new branchCircuit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BranchCircuitDTO result = branchCircuitService.save(branchCircuitDTO);
        return ResponseEntity
            .created(new URI("/api/branch-circuits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /branch-circuits/:id} : Updates an existing branchCircuit.
     *
     * @param id the id of the branchCircuitDTO to save.
     * @param branchCircuitDTO the branchCircuitDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated branchCircuitDTO,
     * or with status {@code 400 (Bad Request)} if the branchCircuitDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the branchCircuitDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<BranchCircuitDTO> updateBranchCircuit(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BranchCircuitDTO branchCircuitDTO
    ) throws URISyntaxException {
        log.debug("REST request to update BranchCircuit : {}, {}", id, branchCircuitDTO);
        if (branchCircuitDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, branchCircuitDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!branchCircuitRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BranchCircuitDTO result = branchCircuitService.update(branchCircuitDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, branchCircuitDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /branch-circuits/:id} : Partial updates given fields of an existing branchCircuit, field will ignore if it is null
     *
     * @param id the id of the branchCircuitDTO to save.
     * @param branchCircuitDTO the branchCircuitDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated branchCircuitDTO,
     * or with status {@code 400 (Bad Request)} if the branchCircuitDTO is not valid,
     * or with status {@code 404 (Not Found)} if the branchCircuitDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the branchCircuitDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BranchCircuitDTO> partialUpdateBranchCircuit(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BranchCircuitDTO branchCircuitDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update BranchCircuit partially : {}, {}", id, branchCircuitDTO);
        if (branchCircuitDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, branchCircuitDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!branchCircuitRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BranchCircuitDTO> result = branchCircuitService.partialUpdate(branchCircuitDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, branchCircuitDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /branch-circuits} : get all the branchCircuits.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of branchCircuits in body.
     */
    @GetMapping("")
    public List<BranchCircuitDTO> getAllBranchCircuits(
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get all BranchCircuits");
        return branchCircuitService.findAll();
    }

    /**
     * {@code GET  /branch-circuits/:id} : get the "id" branchCircuit.
     *
     * @param id the id of the branchCircuitDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the branchCircuitDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<BranchCircuitDTO> getBranchCircuit(@PathVariable("id") Long id) {
        log.debug("REST request to get BranchCircuit : {}", id);
        Optional<BranchCircuitDTO> branchCircuitDTO = branchCircuitService.findOne(id);
        return ResponseUtil.wrapOrNotFound(branchCircuitDTO);
    }

    /**
     * {@code DELETE  /branch-circuits/:id} : delete the "id" branchCircuit.
     *
     * @param id the id of the branchCircuitDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBranchCircuit(@PathVariable("id") Long id) {
        log.debug("REST request to delete BranchCircuit : {}", id);
        branchCircuitService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
