package com.konnect.app.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.konnect.app.IntegrationTest;
import com.konnect.app.domain.Area;
import com.konnect.app.repository.AreaRepository;
import com.konnect.app.service.dto.AreaDTO;
import com.konnect.app.service.mapper.AreaMapper;
import jakarta.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link AreaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AreaResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DETAIL = "AAAAAAAAAA";
    private static final String UPDATED_DETAIL = "BBBBBBBBBB";

    private static final Instant DEFAULT_PUBLICATION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PUBLICATION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/areas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private AreaMapper areaMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAreaMockMvc;

    private Area area;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Area createEntity(EntityManager em) {
        Area area = new Area().code(DEFAULT_CODE).detail(DEFAULT_DETAIL).publicationDate(DEFAULT_PUBLICATION_DATE);
        return area;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Area createUpdatedEntity(EntityManager em) {
        Area area = new Area().code(UPDATED_CODE).detail(UPDATED_DETAIL).publicationDate(UPDATED_PUBLICATION_DATE);
        return area;
    }

    @BeforeEach
    public void initTest() {
        area = createEntity(em);
    }

    @Test
    @Transactional
    void createArea() throws Exception {
        int databaseSizeBeforeCreate = areaRepository.findAll().size();
        // Create the Area
        AreaDTO areaDTO = areaMapper.toDto(area);
        restAreaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(areaDTO)))
            .andExpect(status().isCreated());

        // Validate the Area in the database
        List<Area> areaList = areaRepository.findAll();
        assertThat(areaList).hasSize(databaseSizeBeforeCreate + 1);
        Area testArea = areaList.get(areaList.size() - 1);
        assertThat(testArea.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testArea.getDetail()).isEqualTo(DEFAULT_DETAIL);
        assertThat(testArea.getPublicationDate()).isEqualTo(DEFAULT_PUBLICATION_DATE);
    }

    @Test
    @Transactional
    void createAreaWithExistingId() throws Exception {
        // Create the Area with an existing ID
        area.setId(1L);
        AreaDTO areaDTO = areaMapper.toDto(area);

        int databaseSizeBeforeCreate = areaRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAreaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(areaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Area in the database
        List<Area> areaList = areaRepository.findAll();
        assertThat(areaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAreas() throws Exception {
        // Initialize the database
        areaRepository.saveAndFlush(area);

        // Get all the areaList
        restAreaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(area.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].detail").value(hasItem(DEFAULT_DETAIL)))
            .andExpect(jsonPath("$.[*].publicationDate").value(hasItem(DEFAULT_PUBLICATION_DATE.toString())));
    }

    @Test
    @Transactional
    void getArea() throws Exception {
        // Initialize the database
        areaRepository.saveAndFlush(area);

        // Get the area
        restAreaMockMvc
            .perform(get(ENTITY_API_URL_ID, area.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(area.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.detail").value(DEFAULT_DETAIL))
            .andExpect(jsonPath("$.publicationDate").value(DEFAULT_PUBLICATION_DATE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingArea() throws Exception {
        // Get the area
        restAreaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingArea() throws Exception {
        // Initialize the database
        areaRepository.saveAndFlush(area);

        int databaseSizeBeforeUpdate = areaRepository.findAll().size();

        // Update the area
        Area updatedArea = areaRepository.findById(area.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedArea are not directly saved in db
        em.detach(updatedArea);
        updatedArea.code(UPDATED_CODE).detail(UPDATED_DETAIL).publicationDate(UPDATED_PUBLICATION_DATE);
        AreaDTO areaDTO = areaMapper.toDto(updatedArea);

        restAreaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, areaDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(areaDTO))
            )
            .andExpect(status().isOk());

        // Validate the Area in the database
        List<Area> areaList = areaRepository.findAll();
        assertThat(areaList).hasSize(databaseSizeBeforeUpdate);
        Area testArea = areaList.get(areaList.size() - 1);
        assertThat(testArea.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testArea.getDetail()).isEqualTo(UPDATED_DETAIL);
        assertThat(testArea.getPublicationDate()).isEqualTo(UPDATED_PUBLICATION_DATE);
    }

    @Test
    @Transactional
    void putNonExistingArea() throws Exception {
        int databaseSizeBeforeUpdate = areaRepository.findAll().size();
        area.setId(longCount.incrementAndGet());

        // Create the Area
        AreaDTO areaDTO = areaMapper.toDto(area);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAreaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, areaDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(areaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Area in the database
        List<Area> areaList = areaRepository.findAll();
        assertThat(areaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchArea() throws Exception {
        int databaseSizeBeforeUpdate = areaRepository.findAll().size();
        area.setId(longCount.incrementAndGet());

        // Create the Area
        AreaDTO areaDTO = areaMapper.toDto(area);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAreaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(areaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Area in the database
        List<Area> areaList = areaRepository.findAll();
        assertThat(areaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamArea() throws Exception {
        int databaseSizeBeforeUpdate = areaRepository.findAll().size();
        area.setId(longCount.incrementAndGet());

        // Create the Area
        AreaDTO areaDTO = areaMapper.toDto(area);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAreaMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(areaDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Area in the database
        List<Area> areaList = areaRepository.findAll();
        assertThat(areaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAreaWithPatch() throws Exception {
        // Initialize the database
        areaRepository.saveAndFlush(area);

        int databaseSizeBeforeUpdate = areaRepository.findAll().size();

        // Update the area using partial update
        Area partialUpdatedArea = new Area();
        partialUpdatedArea.setId(area.getId());

        partialUpdatedArea.code(UPDATED_CODE).publicationDate(UPDATED_PUBLICATION_DATE);

        restAreaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedArea.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedArea))
            )
            .andExpect(status().isOk());

        // Validate the Area in the database
        List<Area> areaList = areaRepository.findAll();
        assertThat(areaList).hasSize(databaseSizeBeforeUpdate);
        Area testArea = areaList.get(areaList.size() - 1);
        assertThat(testArea.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testArea.getDetail()).isEqualTo(DEFAULT_DETAIL);
        assertThat(testArea.getPublicationDate()).isEqualTo(UPDATED_PUBLICATION_DATE);
    }

    @Test
    @Transactional
    void fullUpdateAreaWithPatch() throws Exception {
        // Initialize the database
        areaRepository.saveAndFlush(area);

        int databaseSizeBeforeUpdate = areaRepository.findAll().size();

        // Update the area using partial update
        Area partialUpdatedArea = new Area();
        partialUpdatedArea.setId(area.getId());

        partialUpdatedArea.code(UPDATED_CODE).detail(UPDATED_DETAIL).publicationDate(UPDATED_PUBLICATION_DATE);

        restAreaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedArea.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedArea))
            )
            .andExpect(status().isOk());

        // Validate the Area in the database
        List<Area> areaList = areaRepository.findAll();
        assertThat(areaList).hasSize(databaseSizeBeforeUpdate);
        Area testArea = areaList.get(areaList.size() - 1);
        assertThat(testArea.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testArea.getDetail()).isEqualTo(UPDATED_DETAIL);
        assertThat(testArea.getPublicationDate()).isEqualTo(UPDATED_PUBLICATION_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingArea() throws Exception {
        int databaseSizeBeforeUpdate = areaRepository.findAll().size();
        area.setId(longCount.incrementAndGet());

        // Create the Area
        AreaDTO areaDTO = areaMapper.toDto(area);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAreaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, areaDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(areaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Area in the database
        List<Area> areaList = areaRepository.findAll();
        assertThat(areaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchArea() throws Exception {
        int databaseSizeBeforeUpdate = areaRepository.findAll().size();
        area.setId(longCount.incrementAndGet());

        // Create the Area
        AreaDTO areaDTO = areaMapper.toDto(area);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAreaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(areaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Area in the database
        List<Area> areaList = areaRepository.findAll();
        assertThat(areaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamArea() throws Exception {
        int databaseSizeBeforeUpdate = areaRepository.findAll().size();
        area.setId(longCount.incrementAndGet());

        // Create the Area
        AreaDTO areaDTO = areaMapper.toDto(area);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAreaMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(areaDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Area in the database
        List<Area> areaList = areaRepository.findAll();
        assertThat(areaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteArea() throws Exception {
        // Initialize the database
        areaRepository.saveAndFlush(area);

        int databaseSizeBeforeDelete = areaRepository.findAll().size();

        // Delete the area
        restAreaMockMvc
            .perform(delete(ENTITY_API_URL_ID, area.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Area> areaList = areaRepository.findAll();
        assertThat(areaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
