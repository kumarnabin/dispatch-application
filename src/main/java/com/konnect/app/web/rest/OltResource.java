package com.konnect.app.web.rest;

import com.konnect.app.repository.OltRepository;
import com.konnect.app.service.OltService;
import com.konnect.app.service.dto.OltDTO;
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
 * REST controller for managing {@link com.konnect.app.domain.Olt}.
 */
@RestController
@RequestMapping("/api/olts")
public class OltResource {

    private final Logger log = LoggerFactory.getLogger(OltResource.class);

    private static final String ENTITY_NAME = "olt";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OltService oltService;

    private final OltRepository oltRepository;

    public OltResource(OltService oltService, OltRepository oltRepository) {
        this.oltService = oltService;
        this.oltRepository = oltRepository;
    }

    /**
     * {@code POST  /olts} : Create a new olt.
     *
     * @param oltDTO the oltDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new oltDTO, or with status {@code 400 (Bad Request)} if the olt has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OltDTO> createOlt(@RequestBody OltDTO oltDTO) throws URISyntaxException {
        log.debug("REST request to save Olt : {}", oltDTO);
        if (oltDTO.getId() != null) {
            throw new BadRequestAlertException("A new olt cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OltDTO result = oltService.save(oltDTO);
        return ResponseEntity
            .created(new URI("/api/olts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /olts/:id} : Updates an existing olt.
     *
     * @param id the id of the oltDTO to save.
     * @param oltDTO the oltDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated oltDTO,
     * or with status {@code 400 (Bad Request)} if the oltDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the oltDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OltDTO> updateOlt(@PathVariable(value = "id", required = false) final Long id, @RequestBody OltDTO oltDTO)
        throws URISyntaxException {
        log.debug("REST request to update Olt : {}, {}", id, oltDTO);
        if (oltDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, oltDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!oltRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        OltDTO result = oltService.update(oltDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, oltDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /olts/:id} : Partial updates given fields of an existing olt, field will ignore if it is null
     *
     * @param id the id of the oltDTO to save.
     * @param oltDTO the oltDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated oltDTO,
     * or with status {@code 400 (Bad Request)} if the oltDTO is not valid,
     * or with status {@code 404 (Not Found)} if the oltDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the oltDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OltDTO> partialUpdateOlt(@PathVariable(value = "id", required = false) final Long id, @RequestBody OltDTO oltDTO)
        throws URISyntaxException {
        log.debug("REST request to partial update Olt partially : {}, {}", id, oltDTO);
        if (oltDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, oltDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!oltRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OltDTO> result = oltService.partialUpdate(oltDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, oltDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /olts} : get all the olts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of olts in body.
     */
    @GetMapping("")
    public ResponseEntity<List<OltDTO>> getAllOlts(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Olts");
        Page<OltDTO> page = oltService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /olts/:id} : get the "id" olt.
     *
     * @param id the id of the oltDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the oltDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OltDTO> getOlt(@PathVariable("id") Long id) {
        log.debug("REST request to get Olt : {}", id);
        Optional<OltDTO> oltDTO = oltService.findOne(id);
        return ResponseUtil.wrapOrNotFound(oltDTO);
    }

    /**
     * {@code DELETE  /olts/:id} : delete the "id" olt.
     *
     * @param id the id of the oltDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOlt(@PathVariable("id") Long id) {
        log.debug("REST request to delete Olt : {}", id);
        oltService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
