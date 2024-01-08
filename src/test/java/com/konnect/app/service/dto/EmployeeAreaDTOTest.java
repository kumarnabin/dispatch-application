package com.konnect.app.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.konnect.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EmployeeAreaDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EmployeeAreaDTO.class);
        EmployeeAreaDTO employeeAreaDTO1 = new EmployeeAreaDTO();
        employeeAreaDTO1.setId(1L);
        EmployeeAreaDTO employeeAreaDTO2 = new EmployeeAreaDTO();
        assertThat(employeeAreaDTO1).isNotEqualTo(employeeAreaDTO2);
        employeeAreaDTO2.setId(employeeAreaDTO1.getId());
        assertThat(employeeAreaDTO1).isEqualTo(employeeAreaDTO2);
        employeeAreaDTO2.setId(2L);
        assertThat(employeeAreaDTO1).isNotEqualTo(employeeAreaDTO2);
        employeeAreaDTO1.setId(null);
        assertThat(employeeAreaDTO1).isNotEqualTo(employeeAreaDTO2);
    }
}
