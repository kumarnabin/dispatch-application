package com.konnect.app.domain;

import static com.konnect.app.domain.EmployeeAreaTestSamples.*;
import static com.konnect.app.domain.EmployeeTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.konnect.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EmployeeAreaTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EmployeeArea.class);
        EmployeeArea employeeArea1 = getEmployeeAreaSample1();
        EmployeeArea employeeArea2 = new EmployeeArea();
        assertThat(employeeArea1).isNotEqualTo(employeeArea2);

        employeeArea2.setId(employeeArea1.getId());
        assertThat(employeeArea1).isEqualTo(employeeArea2);

        employeeArea2 = getEmployeeAreaSample2();
        assertThat(employeeArea1).isNotEqualTo(employeeArea2);
    }

    @Test
    void employeeTest() throws Exception {
        EmployeeArea employeeArea = getEmployeeAreaRandomSampleGenerator();
        Employee employeeBack = getEmployeeRandomSampleGenerator();

        employeeArea.setEmployee(employeeBack);
        assertThat(employeeArea.getEmployee()).isEqualTo(employeeBack);

        employeeArea.employee(null);
        assertThat(employeeArea.getEmployee()).isNull();
    }
}
