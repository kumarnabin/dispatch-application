package com.konnect.app.domain;

import static com.konnect.app.domain.AreaTestSamples.*;
import static com.konnect.app.domain.DispatchRecordTestSamples.*;
import static com.konnect.app.domain.EmployeeTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.konnect.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DispatchRecordTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DispatchRecord.class);
        DispatchRecord dispatchRecord1 = getDispatchRecordSample1();
        DispatchRecord dispatchRecord2 = new DispatchRecord();
        assertThat(dispatchRecord1).isNotEqualTo(dispatchRecord2);

        dispatchRecord2.setId(dispatchRecord1.getId());
        assertThat(dispatchRecord1).isEqualTo(dispatchRecord2);

        dispatchRecord2 = getDispatchRecordSample2();
        assertThat(dispatchRecord1).isNotEqualTo(dispatchRecord2);
    }

    @Test
    void employeeTest() throws Exception {
        DispatchRecord dispatchRecord = getDispatchRecordRandomSampleGenerator();
        Employee employeeBack = getEmployeeRandomSampleGenerator();

        dispatchRecord.setEmployee(employeeBack);
        assertThat(dispatchRecord.getEmployee()).isEqualTo(employeeBack);

        dispatchRecord.employee(null);
        assertThat(dispatchRecord.getEmployee()).isNull();
    }

    @Test
    void areaTest() throws Exception {
        DispatchRecord dispatchRecord = getDispatchRecordRandomSampleGenerator();
        Area areaBack = getAreaRandomSampleGenerator();

        dispatchRecord.setArea(areaBack);
        assertThat(dispatchRecord.getArea()).isEqualTo(areaBack);

        dispatchRecord.area(null);
        assertThat(dispatchRecord.getArea()).isNull();
    }
}
