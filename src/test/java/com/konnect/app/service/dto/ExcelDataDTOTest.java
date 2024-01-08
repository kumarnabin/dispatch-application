package com.konnect.app.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.konnect.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ExcelDataDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ExcelDataDTO.class);
        ExcelDataDTO excelDataDTO1 = new ExcelDataDTO();
        excelDataDTO1.setId(1L);
        ExcelDataDTO excelDataDTO2 = new ExcelDataDTO();
        assertThat(excelDataDTO1).isNotEqualTo(excelDataDTO2);
        excelDataDTO2.setId(excelDataDTO1.getId());
        assertThat(excelDataDTO1).isEqualTo(excelDataDTO2);
        excelDataDTO2.setId(2L);
        assertThat(excelDataDTO1).isNotEqualTo(excelDataDTO2);
        excelDataDTO1.setId(null);
        assertThat(excelDataDTO1).isNotEqualTo(excelDataDTO2);
    }
}
