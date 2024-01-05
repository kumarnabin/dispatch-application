package com.konnect.app.domain;

import static com.konnect.app.domain.DispatchRecordTestSamples.*;
import static com.konnect.app.domain.DispatchTestSamples.*;
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
    void dispatchTest() throws Exception {
        DispatchRecord dispatchRecord = getDispatchRecordRandomSampleGenerator();
        Dispatch dispatchBack = getDispatchRandomSampleGenerator();

        dispatchRecord.setDispatch(dispatchBack);
        assertThat(dispatchRecord.getDispatch()).isEqualTo(dispatchBack);

        dispatchRecord.dispatch(null);
        assertThat(dispatchRecord.getDispatch()).isNull();
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
}
