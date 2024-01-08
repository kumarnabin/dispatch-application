package com.konnect.app.domain;

import static com.konnect.app.domain.DocumentTestSamples.*;
import static com.konnect.app.domain.EmployeeTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.konnect.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DocumentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Document.class);
        Document document1 = getDocumentSample1();
        Document document2 = new Document();
        assertThat(document1).isNotEqualTo(document2);

        document2.setId(document1.getId());
        assertThat(document1).isEqualTo(document2);

        document2 = getDocumentSample2();
        assertThat(document1).isNotEqualTo(document2);
    }

    @Test
    void employeeTest() throws Exception {
        Document document = getDocumentRandomSampleGenerator();
        Employee employeeBack = getEmployeeRandomSampleGenerator();

        document.setEmployee(employeeBack);
        assertThat(document.getEmployee()).isEqualTo(employeeBack);

        document.employee(null);
        assertThat(document.getEmployee()).isNull();
    }
}
