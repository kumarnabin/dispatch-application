package com.konnect.app.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.konnect.app.IntegrationTest;
import com.konnect.app.domain.ExcelData;
import com.konnect.app.repository.ExcelDataRepository;
import com.konnect.app.service.dto.ExcelDataDTO;
import com.konnect.app.service.mapper.ExcelDataMapper;
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
 * Integration tests for the {@link ExcelDataResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ExcelDataResourceIT {

    private static final String DEFAULT_COLUMN_1 = "AAAAAAAAAA";
    private static final String UPDATED_COLUMN_1 = "BBBBBBBBBB";

    private static final String DEFAULT_COLUMN_2 = "AAAAAAAAAA";
    private static final String UPDATED_COLUMN_2 = "BBBBBBBBBB";

    private static final String DEFAULT_COLUMN_3 = "AAAAAAAAAA";
    private static final String UPDATED_COLUMN_3 = "BBBBBBBBBB";

    private static final String DEFAULT_COLUMN_4 = "AAAAAAAAAA";
    private static final String UPDATED_COLUMN_4 = "BBBBBBBBBB";

    private static final String DEFAULT_COLUMN_5 = "AAAAAAAAAA";
    private static final String UPDATED_COLUMN_5 = "BBBBBBBBBB";

    private static final String DEFAULT_COLUMN_6 = "AAAAAAAAAA";
    private static final String UPDATED_COLUMN_6 = "BBBBBBBBBB";

    private static final String DEFAULT_COLUMN_7 = "AAAAAAAAAA";
    private static final String UPDATED_COLUMN_7 = "BBBBBBBBBB";

    private static final String DEFAULT_COLUMN_8 = "AAAAAAAAAA";
    private static final String UPDATED_COLUMN_8 = "BBBBBBBBBB";

    private static final String DEFAULT_COLUMN_9 = "AAAAAAAAAA";
    private static final String UPDATED_COLUMN_9 = "BBBBBBBBBB";

    private static final String DEFAULT_COLUMN_10 = "AAAAAAAAAA";
    private static final String UPDATED_COLUMN_10 = "BBBBBBBBBB";

    private static final String DEFAULT_COLUMN_11 = "AAAAAAAAAA";
    private static final String UPDATED_COLUMN_11 = "BBBBBBBBBB";

    private static final String DEFAULT_COLUMN_12 = "AAAAAAAAAA";
    private static final String UPDATED_COLUMN_12 = "BBBBBBBBBB";

    private static final String DEFAULT_COLUMN_13 = "AAAAAAAAAA";
    private static final String UPDATED_COLUMN_13 = "BBBBBBBBBB";

    private static final String DEFAULT_COLUMN_14 = "AAAAAAAAAA";
    private static final String UPDATED_COLUMN_14 = "BBBBBBBBBB";

    private static final String DEFAULT_COLUMN_15 = "AAAAAAAAAA";
    private static final String UPDATED_COLUMN_15 = "BBBBBBBBBB";

    private static final String DEFAULT_COLUMN_16 = "AAAAAAAAAA";
    private static final String UPDATED_COLUMN_16 = "BBBBBBBBBB";

    private static final String DEFAULT_COLUMN_17 = "AAAAAAAAAA";
    private static final String UPDATED_COLUMN_17 = "BBBBBBBBBB";

    private static final String DEFAULT_COLUMN_18 = "AAAAAAAAAA";
    private static final String UPDATED_COLUMN_18 = "BBBBBBBBBB";

    private static final String DEFAULT_COLUMN_19 = "AAAAAAAAAA";
    private static final String UPDATED_COLUMN_19 = "BBBBBBBBBB";

    private static final String DEFAULT_COLUMN_20 = "AAAAAAAAAA";
    private static final String UPDATED_COLUMN_20 = "BBBBBBBBBB";

    private static final String DEFAULT_COLUMN_21 = "AAAAAAAAAA";
    private static final String UPDATED_COLUMN_21 = "BBBBBBBBBB";

    private static final String DEFAULT_COLUMN_22 = "AAAAAAAAAA";
    private static final String UPDATED_COLUMN_22 = "BBBBBBBBBB";

    private static final String DEFAULT_COLUMN_23 = "AAAAAAAAAA";
    private static final String UPDATED_COLUMN_23 = "BBBBBBBBBB";

    private static final String DEFAULT_COLUMN_24 = "AAAAAAAAAA";
    private static final String UPDATED_COLUMN_24 = "BBBBBBBBBB";

    private static final String DEFAULT_COLUMN_25 = "AAAAAAAAAA";
    private static final String UPDATED_COLUMN_25 = "BBBBBBBBBB";

    private static final String DEFAULT_COLUMN_26 = "AAAAAAAAAA";
    private static final String UPDATED_COLUMN_26 = "BBBBBBBBBB";

    private static final String DEFAULT_COLUMN_27 = "AAAAAAAAAA";
    private static final String UPDATED_COLUMN_27 = "BBBBBBBBBB";

    private static final Instant DEFAULT_PUBLICATION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PUBLICATION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/excel-data";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ExcelDataRepository excelDataRepository;

    @Autowired
    private ExcelDataMapper excelDataMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restExcelDataMockMvc;

    private ExcelData excelData;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ExcelData createEntity(EntityManager em) {
        ExcelData excelData = new ExcelData()
            .column1(DEFAULT_COLUMN_1)
            .column2(DEFAULT_COLUMN_2)
            .column3(DEFAULT_COLUMN_3)
            .column4(DEFAULT_COLUMN_4)
            .column5(DEFAULT_COLUMN_5)
            .column6(DEFAULT_COLUMN_6)
            .column7(DEFAULT_COLUMN_7)
            .column8(DEFAULT_COLUMN_8)
            .column9(DEFAULT_COLUMN_9)
            .column10(DEFAULT_COLUMN_10)
            .column11(DEFAULT_COLUMN_11)
            .column12(DEFAULT_COLUMN_12)
            .column13(DEFAULT_COLUMN_13)
            .column14(DEFAULT_COLUMN_14)
            .column15(DEFAULT_COLUMN_15)
            .column16(DEFAULT_COLUMN_16)
            .column17(DEFAULT_COLUMN_17)
            .column18(DEFAULT_COLUMN_18)
            .column19(DEFAULT_COLUMN_19)
            .column20(DEFAULT_COLUMN_20)
            .column21(DEFAULT_COLUMN_21)
            .column22(DEFAULT_COLUMN_22)
            .column23(DEFAULT_COLUMN_23)
            .column24(DEFAULT_COLUMN_24)
            .column25(DEFAULT_COLUMN_25)
            .column26(DEFAULT_COLUMN_26)
            .column27(DEFAULT_COLUMN_27)
            .publicationDate(DEFAULT_PUBLICATION_DATE);
        return excelData;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ExcelData createUpdatedEntity(EntityManager em) {
        ExcelData excelData = new ExcelData()
            .column1(UPDATED_COLUMN_1)
            .column2(UPDATED_COLUMN_2)
            .column3(UPDATED_COLUMN_3)
            .column4(UPDATED_COLUMN_4)
            .column5(UPDATED_COLUMN_5)
            .column6(UPDATED_COLUMN_6)
            .column7(UPDATED_COLUMN_7)
            .column8(UPDATED_COLUMN_8)
            .column9(UPDATED_COLUMN_9)
            .column10(UPDATED_COLUMN_10)
            .column11(UPDATED_COLUMN_11)
            .column12(UPDATED_COLUMN_12)
            .column13(UPDATED_COLUMN_13)
            .column14(UPDATED_COLUMN_14)
            .column15(UPDATED_COLUMN_15)
            .column16(UPDATED_COLUMN_16)
            .column17(UPDATED_COLUMN_17)
            .column18(UPDATED_COLUMN_18)
            .column19(UPDATED_COLUMN_19)
            .column20(UPDATED_COLUMN_20)
            .column21(UPDATED_COLUMN_21)
            .column22(UPDATED_COLUMN_22)
            .column23(UPDATED_COLUMN_23)
            .column24(UPDATED_COLUMN_24)
            .column25(UPDATED_COLUMN_25)
            .column26(UPDATED_COLUMN_26)
            .column27(UPDATED_COLUMN_27)
            .publicationDate(UPDATED_PUBLICATION_DATE);
        return excelData;
    }

    @BeforeEach
    public void initTest() {
        excelData = createEntity(em);
    }

    @Test
    @Transactional
    void createExcelData() throws Exception {
        int databaseSizeBeforeCreate = excelDataRepository.findAll().size();
        // Create the ExcelData
        ExcelDataDTO excelDataDTO = excelDataMapper.toDto(excelData);
        restExcelDataMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(excelDataDTO)))
            .andExpect(status().isCreated());

        // Validate the ExcelData in the database
        List<ExcelData> excelDataList = excelDataRepository.findAll();
        assertThat(excelDataList).hasSize(databaseSizeBeforeCreate + 1);
        ExcelData testExcelData = excelDataList.get(excelDataList.size() - 1);
        assertThat(testExcelData.getColumn1()).isEqualTo(DEFAULT_COLUMN_1);
        assertThat(testExcelData.getColumn2()).isEqualTo(DEFAULT_COLUMN_2);
        assertThat(testExcelData.getColumn3()).isEqualTo(DEFAULT_COLUMN_3);
        assertThat(testExcelData.getColumn4()).isEqualTo(DEFAULT_COLUMN_4);
        assertThat(testExcelData.getColumn5()).isEqualTo(DEFAULT_COLUMN_5);
        assertThat(testExcelData.getColumn6()).isEqualTo(DEFAULT_COLUMN_6);
        assertThat(testExcelData.getColumn7()).isEqualTo(DEFAULT_COLUMN_7);
        assertThat(testExcelData.getColumn8()).isEqualTo(DEFAULT_COLUMN_8);
        assertThat(testExcelData.getColumn9()).isEqualTo(DEFAULT_COLUMN_9);
        assertThat(testExcelData.getColumn10()).isEqualTo(DEFAULT_COLUMN_10);
        assertThat(testExcelData.getColumn11()).isEqualTo(DEFAULT_COLUMN_11);
        assertThat(testExcelData.getColumn12()).isEqualTo(DEFAULT_COLUMN_12);
        assertThat(testExcelData.getColumn13()).isEqualTo(DEFAULT_COLUMN_13);
        assertThat(testExcelData.getColumn14()).isEqualTo(DEFAULT_COLUMN_14);
        assertThat(testExcelData.getColumn15()).isEqualTo(DEFAULT_COLUMN_15);
        assertThat(testExcelData.getColumn16()).isEqualTo(DEFAULT_COLUMN_16);
        assertThat(testExcelData.getColumn17()).isEqualTo(DEFAULT_COLUMN_17);
        assertThat(testExcelData.getColumn18()).isEqualTo(DEFAULT_COLUMN_18);
        assertThat(testExcelData.getColumn19()).isEqualTo(DEFAULT_COLUMN_19);
        assertThat(testExcelData.getColumn20()).isEqualTo(DEFAULT_COLUMN_20);
        assertThat(testExcelData.getColumn21()).isEqualTo(DEFAULT_COLUMN_21);
        assertThat(testExcelData.getColumn22()).isEqualTo(DEFAULT_COLUMN_22);
        assertThat(testExcelData.getColumn23()).isEqualTo(DEFAULT_COLUMN_23);
        assertThat(testExcelData.getColumn24()).isEqualTo(DEFAULT_COLUMN_24);
        assertThat(testExcelData.getColumn25()).isEqualTo(DEFAULT_COLUMN_25);
        assertThat(testExcelData.getColumn26()).isEqualTo(DEFAULT_COLUMN_26);
        assertThat(testExcelData.getColumn27()).isEqualTo(DEFAULT_COLUMN_27);
        assertThat(testExcelData.getPublicationDate()).isEqualTo(DEFAULT_PUBLICATION_DATE);
    }

    @Test
    @Transactional
    void createExcelDataWithExistingId() throws Exception {
        // Create the ExcelData with an existing ID
        excelData.setId(1L);
        ExcelDataDTO excelDataDTO = excelDataMapper.toDto(excelData);

        int databaseSizeBeforeCreate = excelDataRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restExcelDataMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(excelDataDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ExcelData in the database
        List<ExcelData> excelDataList = excelDataRepository.findAll();
        assertThat(excelDataList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllExcelData() throws Exception {
        // Initialize the database
        excelDataRepository.saveAndFlush(excelData);

        // Get all the excelDataList
        restExcelDataMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(excelData.getId().intValue())))
            .andExpect(jsonPath("$.[*].column1").value(hasItem(DEFAULT_COLUMN_1)))
            .andExpect(jsonPath("$.[*].column2").value(hasItem(DEFAULT_COLUMN_2)))
            .andExpect(jsonPath("$.[*].column3").value(hasItem(DEFAULT_COLUMN_3)))
            .andExpect(jsonPath("$.[*].column4").value(hasItem(DEFAULT_COLUMN_4)))
            .andExpect(jsonPath("$.[*].column5").value(hasItem(DEFAULT_COLUMN_5)))
            .andExpect(jsonPath("$.[*].column6").value(hasItem(DEFAULT_COLUMN_6)))
            .andExpect(jsonPath("$.[*].column7").value(hasItem(DEFAULT_COLUMN_7)))
            .andExpect(jsonPath("$.[*].column8").value(hasItem(DEFAULT_COLUMN_8)))
            .andExpect(jsonPath("$.[*].column9").value(hasItem(DEFAULT_COLUMN_9)))
            .andExpect(jsonPath("$.[*].column10").value(hasItem(DEFAULT_COLUMN_10)))
            .andExpect(jsonPath("$.[*].column11").value(hasItem(DEFAULT_COLUMN_11)))
            .andExpect(jsonPath("$.[*].column12").value(hasItem(DEFAULT_COLUMN_12)))
            .andExpect(jsonPath("$.[*].column13").value(hasItem(DEFAULT_COLUMN_13)))
            .andExpect(jsonPath("$.[*].column14").value(hasItem(DEFAULT_COLUMN_14)))
            .andExpect(jsonPath("$.[*].column15").value(hasItem(DEFAULT_COLUMN_15)))
            .andExpect(jsonPath("$.[*].column16").value(hasItem(DEFAULT_COLUMN_16)))
            .andExpect(jsonPath("$.[*].column17").value(hasItem(DEFAULT_COLUMN_17)))
            .andExpect(jsonPath("$.[*].column18").value(hasItem(DEFAULT_COLUMN_18)))
            .andExpect(jsonPath("$.[*].column19").value(hasItem(DEFAULT_COLUMN_19)))
            .andExpect(jsonPath("$.[*].column20").value(hasItem(DEFAULT_COLUMN_20)))
            .andExpect(jsonPath("$.[*].column21").value(hasItem(DEFAULT_COLUMN_21)))
            .andExpect(jsonPath("$.[*].column22").value(hasItem(DEFAULT_COLUMN_22)))
            .andExpect(jsonPath("$.[*].column23").value(hasItem(DEFAULT_COLUMN_23)))
            .andExpect(jsonPath("$.[*].column24").value(hasItem(DEFAULT_COLUMN_24)))
            .andExpect(jsonPath("$.[*].column25").value(hasItem(DEFAULT_COLUMN_25)))
            .andExpect(jsonPath("$.[*].column26").value(hasItem(DEFAULT_COLUMN_26)))
            .andExpect(jsonPath("$.[*].column27").value(hasItem(DEFAULT_COLUMN_27)))
            .andExpect(jsonPath("$.[*].publicationDate").value(hasItem(DEFAULT_PUBLICATION_DATE.toString())));
    }

    @Test
    @Transactional
    void getExcelData() throws Exception {
        // Initialize the database
        excelDataRepository.saveAndFlush(excelData);

        // Get the excelData
        restExcelDataMockMvc
            .perform(get(ENTITY_API_URL_ID, excelData.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(excelData.getId().intValue()))
            .andExpect(jsonPath("$.column1").value(DEFAULT_COLUMN_1))
            .andExpect(jsonPath("$.column2").value(DEFAULT_COLUMN_2))
            .andExpect(jsonPath("$.column3").value(DEFAULT_COLUMN_3))
            .andExpect(jsonPath("$.column4").value(DEFAULT_COLUMN_4))
            .andExpect(jsonPath("$.column5").value(DEFAULT_COLUMN_5))
            .andExpect(jsonPath("$.column6").value(DEFAULT_COLUMN_6))
            .andExpect(jsonPath("$.column7").value(DEFAULT_COLUMN_7))
            .andExpect(jsonPath("$.column8").value(DEFAULT_COLUMN_8))
            .andExpect(jsonPath("$.column9").value(DEFAULT_COLUMN_9))
            .andExpect(jsonPath("$.column10").value(DEFAULT_COLUMN_10))
            .andExpect(jsonPath("$.column11").value(DEFAULT_COLUMN_11))
            .andExpect(jsonPath("$.column12").value(DEFAULT_COLUMN_12))
            .andExpect(jsonPath("$.column13").value(DEFAULT_COLUMN_13))
            .andExpect(jsonPath("$.column14").value(DEFAULT_COLUMN_14))
            .andExpect(jsonPath("$.column15").value(DEFAULT_COLUMN_15))
            .andExpect(jsonPath("$.column16").value(DEFAULT_COLUMN_16))
            .andExpect(jsonPath("$.column17").value(DEFAULT_COLUMN_17))
            .andExpect(jsonPath("$.column18").value(DEFAULT_COLUMN_18))
            .andExpect(jsonPath("$.column19").value(DEFAULT_COLUMN_19))
            .andExpect(jsonPath("$.column20").value(DEFAULT_COLUMN_20))
            .andExpect(jsonPath("$.column21").value(DEFAULT_COLUMN_21))
            .andExpect(jsonPath("$.column22").value(DEFAULT_COLUMN_22))
            .andExpect(jsonPath("$.column23").value(DEFAULT_COLUMN_23))
            .andExpect(jsonPath("$.column24").value(DEFAULT_COLUMN_24))
            .andExpect(jsonPath("$.column25").value(DEFAULT_COLUMN_25))
            .andExpect(jsonPath("$.column26").value(DEFAULT_COLUMN_26))
            .andExpect(jsonPath("$.column27").value(DEFAULT_COLUMN_27))
            .andExpect(jsonPath("$.publicationDate").value(DEFAULT_PUBLICATION_DATE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingExcelData() throws Exception {
        // Get the excelData
        restExcelDataMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingExcelData() throws Exception {
        // Initialize the database
        excelDataRepository.saveAndFlush(excelData);

        int databaseSizeBeforeUpdate = excelDataRepository.findAll().size();

        // Update the excelData
        ExcelData updatedExcelData = excelDataRepository.findById(excelData.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedExcelData are not directly saved in db
        em.detach(updatedExcelData);
        updatedExcelData
            .column1(UPDATED_COLUMN_1)
            .column2(UPDATED_COLUMN_2)
            .column3(UPDATED_COLUMN_3)
            .column4(UPDATED_COLUMN_4)
            .column5(UPDATED_COLUMN_5)
            .column6(UPDATED_COLUMN_6)
            .column7(UPDATED_COLUMN_7)
            .column8(UPDATED_COLUMN_8)
            .column9(UPDATED_COLUMN_9)
            .column10(UPDATED_COLUMN_10)
            .column11(UPDATED_COLUMN_11)
            .column12(UPDATED_COLUMN_12)
            .column13(UPDATED_COLUMN_13)
            .column14(UPDATED_COLUMN_14)
            .column15(UPDATED_COLUMN_15)
            .column16(UPDATED_COLUMN_16)
            .column17(UPDATED_COLUMN_17)
            .column18(UPDATED_COLUMN_18)
            .column19(UPDATED_COLUMN_19)
            .column20(UPDATED_COLUMN_20)
            .column21(UPDATED_COLUMN_21)
            .column22(UPDATED_COLUMN_22)
            .column23(UPDATED_COLUMN_23)
            .column24(UPDATED_COLUMN_24)
            .column25(UPDATED_COLUMN_25)
            .column26(UPDATED_COLUMN_26)
            .column27(UPDATED_COLUMN_27)
            .publicationDate(UPDATED_PUBLICATION_DATE);
        ExcelDataDTO excelDataDTO = excelDataMapper.toDto(updatedExcelData);

        restExcelDataMockMvc
            .perform(
                put(ENTITY_API_URL_ID, excelDataDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(excelDataDTO))
            )
            .andExpect(status().isOk());

        // Validate the ExcelData in the database
        List<ExcelData> excelDataList = excelDataRepository.findAll();
        assertThat(excelDataList).hasSize(databaseSizeBeforeUpdate);
        ExcelData testExcelData = excelDataList.get(excelDataList.size() - 1);
        assertThat(testExcelData.getColumn1()).isEqualTo(UPDATED_COLUMN_1);
        assertThat(testExcelData.getColumn2()).isEqualTo(UPDATED_COLUMN_2);
        assertThat(testExcelData.getColumn3()).isEqualTo(UPDATED_COLUMN_3);
        assertThat(testExcelData.getColumn4()).isEqualTo(UPDATED_COLUMN_4);
        assertThat(testExcelData.getColumn5()).isEqualTo(UPDATED_COLUMN_5);
        assertThat(testExcelData.getColumn6()).isEqualTo(UPDATED_COLUMN_6);
        assertThat(testExcelData.getColumn7()).isEqualTo(UPDATED_COLUMN_7);
        assertThat(testExcelData.getColumn8()).isEqualTo(UPDATED_COLUMN_8);
        assertThat(testExcelData.getColumn9()).isEqualTo(UPDATED_COLUMN_9);
        assertThat(testExcelData.getColumn10()).isEqualTo(UPDATED_COLUMN_10);
        assertThat(testExcelData.getColumn11()).isEqualTo(UPDATED_COLUMN_11);
        assertThat(testExcelData.getColumn12()).isEqualTo(UPDATED_COLUMN_12);
        assertThat(testExcelData.getColumn13()).isEqualTo(UPDATED_COLUMN_13);
        assertThat(testExcelData.getColumn14()).isEqualTo(UPDATED_COLUMN_14);
        assertThat(testExcelData.getColumn15()).isEqualTo(UPDATED_COLUMN_15);
        assertThat(testExcelData.getColumn16()).isEqualTo(UPDATED_COLUMN_16);
        assertThat(testExcelData.getColumn17()).isEqualTo(UPDATED_COLUMN_17);
        assertThat(testExcelData.getColumn18()).isEqualTo(UPDATED_COLUMN_18);
        assertThat(testExcelData.getColumn19()).isEqualTo(UPDATED_COLUMN_19);
        assertThat(testExcelData.getColumn20()).isEqualTo(UPDATED_COLUMN_20);
        assertThat(testExcelData.getColumn21()).isEqualTo(UPDATED_COLUMN_21);
        assertThat(testExcelData.getColumn22()).isEqualTo(UPDATED_COLUMN_22);
        assertThat(testExcelData.getColumn23()).isEqualTo(UPDATED_COLUMN_23);
        assertThat(testExcelData.getColumn24()).isEqualTo(UPDATED_COLUMN_24);
        assertThat(testExcelData.getColumn25()).isEqualTo(UPDATED_COLUMN_25);
        assertThat(testExcelData.getColumn26()).isEqualTo(UPDATED_COLUMN_26);
        assertThat(testExcelData.getColumn27()).isEqualTo(UPDATED_COLUMN_27);
        assertThat(testExcelData.getPublicationDate()).isEqualTo(UPDATED_PUBLICATION_DATE);
    }

    @Test
    @Transactional
    void putNonExistingExcelData() throws Exception {
        int databaseSizeBeforeUpdate = excelDataRepository.findAll().size();
        excelData.setId(longCount.incrementAndGet());

        // Create the ExcelData
        ExcelDataDTO excelDataDTO = excelDataMapper.toDto(excelData);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restExcelDataMockMvc
            .perform(
                put(ENTITY_API_URL_ID, excelDataDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(excelDataDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ExcelData in the database
        List<ExcelData> excelDataList = excelDataRepository.findAll();
        assertThat(excelDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchExcelData() throws Exception {
        int databaseSizeBeforeUpdate = excelDataRepository.findAll().size();
        excelData.setId(longCount.incrementAndGet());

        // Create the ExcelData
        ExcelDataDTO excelDataDTO = excelDataMapper.toDto(excelData);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restExcelDataMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(excelDataDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ExcelData in the database
        List<ExcelData> excelDataList = excelDataRepository.findAll();
        assertThat(excelDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamExcelData() throws Exception {
        int databaseSizeBeforeUpdate = excelDataRepository.findAll().size();
        excelData.setId(longCount.incrementAndGet());

        // Create the ExcelData
        ExcelDataDTO excelDataDTO = excelDataMapper.toDto(excelData);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restExcelDataMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(excelDataDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ExcelData in the database
        List<ExcelData> excelDataList = excelDataRepository.findAll();
        assertThat(excelDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateExcelDataWithPatch() throws Exception {
        // Initialize the database
        excelDataRepository.saveAndFlush(excelData);

        int databaseSizeBeforeUpdate = excelDataRepository.findAll().size();

        // Update the excelData using partial update
        ExcelData partialUpdatedExcelData = new ExcelData();
        partialUpdatedExcelData.setId(excelData.getId());

        partialUpdatedExcelData
            .column1(UPDATED_COLUMN_1)
            .column2(UPDATED_COLUMN_2)
            .column3(UPDATED_COLUMN_3)
            .column5(UPDATED_COLUMN_5)
            .column6(UPDATED_COLUMN_6)
            .column9(UPDATED_COLUMN_9)
            .column11(UPDATED_COLUMN_11)
            .column12(UPDATED_COLUMN_12)
            .column14(UPDATED_COLUMN_14)
            .column16(UPDATED_COLUMN_16)
            .column17(UPDATED_COLUMN_17)
            .column20(UPDATED_COLUMN_20)
            .publicationDate(UPDATED_PUBLICATION_DATE);

        restExcelDataMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedExcelData.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedExcelData))
            )
            .andExpect(status().isOk());

        // Validate the ExcelData in the database
        List<ExcelData> excelDataList = excelDataRepository.findAll();
        assertThat(excelDataList).hasSize(databaseSizeBeforeUpdate);
        ExcelData testExcelData = excelDataList.get(excelDataList.size() - 1);
        assertThat(testExcelData.getColumn1()).isEqualTo(UPDATED_COLUMN_1);
        assertThat(testExcelData.getColumn2()).isEqualTo(UPDATED_COLUMN_2);
        assertThat(testExcelData.getColumn3()).isEqualTo(UPDATED_COLUMN_3);
        assertThat(testExcelData.getColumn4()).isEqualTo(DEFAULT_COLUMN_4);
        assertThat(testExcelData.getColumn5()).isEqualTo(UPDATED_COLUMN_5);
        assertThat(testExcelData.getColumn6()).isEqualTo(UPDATED_COLUMN_6);
        assertThat(testExcelData.getColumn7()).isEqualTo(DEFAULT_COLUMN_7);
        assertThat(testExcelData.getColumn8()).isEqualTo(DEFAULT_COLUMN_8);
        assertThat(testExcelData.getColumn9()).isEqualTo(UPDATED_COLUMN_9);
        assertThat(testExcelData.getColumn10()).isEqualTo(DEFAULT_COLUMN_10);
        assertThat(testExcelData.getColumn11()).isEqualTo(UPDATED_COLUMN_11);
        assertThat(testExcelData.getColumn12()).isEqualTo(UPDATED_COLUMN_12);
        assertThat(testExcelData.getColumn13()).isEqualTo(DEFAULT_COLUMN_13);
        assertThat(testExcelData.getColumn14()).isEqualTo(UPDATED_COLUMN_14);
        assertThat(testExcelData.getColumn15()).isEqualTo(DEFAULT_COLUMN_15);
        assertThat(testExcelData.getColumn16()).isEqualTo(UPDATED_COLUMN_16);
        assertThat(testExcelData.getColumn17()).isEqualTo(UPDATED_COLUMN_17);
        assertThat(testExcelData.getColumn18()).isEqualTo(DEFAULT_COLUMN_18);
        assertThat(testExcelData.getColumn19()).isEqualTo(DEFAULT_COLUMN_19);
        assertThat(testExcelData.getColumn20()).isEqualTo(UPDATED_COLUMN_20);
        assertThat(testExcelData.getColumn21()).isEqualTo(DEFAULT_COLUMN_21);
        assertThat(testExcelData.getColumn22()).isEqualTo(DEFAULT_COLUMN_22);
        assertThat(testExcelData.getColumn23()).isEqualTo(DEFAULT_COLUMN_23);
        assertThat(testExcelData.getColumn24()).isEqualTo(DEFAULT_COLUMN_24);
        assertThat(testExcelData.getColumn25()).isEqualTo(DEFAULT_COLUMN_25);
        assertThat(testExcelData.getColumn26()).isEqualTo(DEFAULT_COLUMN_26);
        assertThat(testExcelData.getColumn27()).isEqualTo(DEFAULT_COLUMN_27);
        assertThat(testExcelData.getPublicationDate()).isEqualTo(UPDATED_PUBLICATION_DATE);
    }

    @Test
    @Transactional
    void fullUpdateExcelDataWithPatch() throws Exception {
        // Initialize the database
        excelDataRepository.saveAndFlush(excelData);

        int databaseSizeBeforeUpdate = excelDataRepository.findAll().size();

        // Update the excelData using partial update
        ExcelData partialUpdatedExcelData = new ExcelData();
        partialUpdatedExcelData.setId(excelData.getId());

        partialUpdatedExcelData
            .column1(UPDATED_COLUMN_1)
            .column2(UPDATED_COLUMN_2)
            .column3(UPDATED_COLUMN_3)
            .column4(UPDATED_COLUMN_4)
            .column5(UPDATED_COLUMN_5)
            .column6(UPDATED_COLUMN_6)
            .column7(UPDATED_COLUMN_7)
            .column8(UPDATED_COLUMN_8)
            .column9(UPDATED_COLUMN_9)
            .column10(UPDATED_COLUMN_10)
            .column11(UPDATED_COLUMN_11)
            .column12(UPDATED_COLUMN_12)
            .column13(UPDATED_COLUMN_13)
            .column14(UPDATED_COLUMN_14)
            .column15(UPDATED_COLUMN_15)
            .column16(UPDATED_COLUMN_16)
            .column17(UPDATED_COLUMN_17)
            .column18(UPDATED_COLUMN_18)
            .column19(UPDATED_COLUMN_19)
            .column20(UPDATED_COLUMN_20)
            .column21(UPDATED_COLUMN_21)
            .column22(UPDATED_COLUMN_22)
            .column23(UPDATED_COLUMN_23)
            .column24(UPDATED_COLUMN_24)
            .column25(UPDATED_COLUMN_25)
            .column26(UPDATED_COLUMN_26)
            .column27(UPDATED_COLUMN_27)
            .publicationDate(UPDATED_PUBLICATION_DATE);

        restExcelDataMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedExcelData.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedExcelData))
            )
            .andExpect(status().isOk());

        // Validate the ExcelData in the database
        List<ExcelData> excelDataList = excelDataRepository.findAll();
        assertThat(excelDataList).hasSize(databaseSizeBeforeUpdate);
        ExcelData testExcelData = excelDataList.get(excelDataList.size() - 1);
        assertThat(testExcelData.getColumn1()).isEqualTo(UPDATED_COLUMN_1);
        assertThat(testExcelData.getColumn2()).isEqualTo(UPDATED_COLUMN_2);
        assertThat(testExcelData.getColumn3()).isEqualTo(UPDATED_COLUMN_3);
        assertThat(testExcelData.getColumn4()).isEqualTo(UPDATED_COLUMN_4);
        assertThat(testExcelData.getColumn5()).isEqualTo(UPDATED_COLUMN_5);
        assertThat(testExcelData.getColumn6()).isEqualTo(UPDATED_COLUMN_6);
        assertThat(testExcelData.getColumn7()).isEqualTo(UPDATED_COLUMN_7);
        assertThat(testExcelData.getColumn8()).isEqualTo(UPDATED_COLUMN_8);
        assertThat(testExcelData.getColumn9()).isEqualTo(UPDATED_COLUMN_9);
        assertThat(testExcelData.getColumn10()).isEqualTo(UPDATED_COLUMN_10);
        assertThat(testExcelData.getColumn11()).isEqualTo(UPDATED_COLUMN_11);
        assertThat(testExcelData.getColumn12()).isEqualTo(UPDATED_COLUMN_12);
        assertThat(testExcelData.getColumn13()).isEqualTo(UPDATED_COLUMN_13);
        assertThat(testExcelData.getColumn14()).isEqualTo(UPDATED_COLUMN_14);
        assertThat(testExcelData.getColumn15()).isEqualTo(UPDATED_COLUMN_15);
        assertThat(testExcelData.getColumn16()).isEqualTo(UPDATED_COLUMN_16);
        assertThat(testExcelData.getColumn17()).isEqualTo(UPDATED_COLUMN_17);
        assertThat(testExcelData.getColumn18()).isEqualTo(UPDATED_COLUMN_18);
        assertThat(testExcelData.getColumn19()).isEqualTo(UPDATED_COLUMN_19);
        assertThat(testExcelData.getColumn20()).isEqualTo(UPDATED_COLUMN_20);
        assertThat(testExcelData.getColumn21()).isEqualTo(UPDATED_COLUMN_21);
        assertThat(testExcelData.getColumn22()).isEqualTo(UPDATED_COLUMN_22);
        assertThat(testExcelData.getColumn23()).isEqualTo(UPDATED_COLUMN_23);
        assertThat(testExcelData.getColumn24()).isEqualTo(UPDATED_COLUMN_24);
        assertThat(testExcelData.getColumn25()).isEqualTo(UPDATED_COLUMN_25);
        assertThat(testExcelData.getColumn26()).isEqualTo(UPDATED_COLUMN_26);
        assertThat(testExcelData.getColumn27()).isEqualTo(UPDATED_COLUMN_27);
        assertThat(testExcelData.getPublicationDate()).isEqualTo(UPDATED_PUBLICATION_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingExcelData() throws Exception {
        int databaseSizeBeforeUpdate = excelDataRepository.findAll().size();
        excelData.setId(longCount.incrementAndGet());

        // Create the ExcelData
        ExcelDataDTO excelDataDTO = excelDataMapper.toDto(excelData);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restExcelDataMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, excelDataDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(excelDataDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ExcelData in the database
        List<ExcelData> excelDataList = excelDataRepository.findAll();
        assertThat(excelDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchExcelData() throws Exception {
        int databaseSizeBeforeUpdate = excelDataRepository.findAll().size();
        excelData.setId(longCount.incrementAndGet());

        // Create the ExcelData
        ExcelDataDTO excelDataDTO = excelDataMapper.toDto(excelData);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restExcelDataMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(excelDataDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ExcelData in the database
        List<ExcelData> excelDataList = excelDataRepository.findAll();
        assertThat(excelDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamExcelData() throws Exception {
        int databaseSizeBeforeUpdate = excelDataRepository.findAll().size();
        excelData.setId(longCount.incrementAndGet());

        // Create the ExcelData
        ExcelDataDTO excelDataDTO = excelDataMapper.toDto(excelData);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restExcelDataMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(excelDataDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ExcelData in the database
        List<ExcelData> excelDataList = excelDataRepository.findAll();
        assertThat(excelDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteExcelData() throws Exception {
        // Initialize the database
        excelDataRepository.saveAndFlush(excelData);

        int databaseSizeBeforeDelete = excelDataRepository.findAll().size();

        // Delete the excelData
        restExcelDataMockMvc
            .perform(delete(ENTITY_API_URL_ID, excelData.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ExcelData> excelDataList = excelDataRepository.findAll();
        assertThat(excelDataList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
