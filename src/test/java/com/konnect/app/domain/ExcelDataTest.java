package com.konnect.app.domain;

import static com.konnect.app.domain.ExcelDataTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.konnect.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ExcelDataTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ExcelData.class);
        ExcelData excelData1 = getExcelDataSample1();
        ExcelData excelData2 = new ExcelData();
        assertThat(excelData1).isNotEqualTo(excelData2);

        excelData2.setId(excelData1.getId());
        assertThat(excelData1).isEqualTo(excelData2);

        excelData2 = getExcelDataSample2();
        assertThat(excelData1).isNotEqualTo(excelData2);
    }
}
