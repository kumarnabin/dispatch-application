package com.konnect.app.domain;

import static com.konnect.app.domain.AreaTestSamples.*;
import static com.konnect.app.domain.EmployeeTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.konnect.app.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class AreaTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Area.class);
        Area area1 = getAreaSample1();
        Area area2 = new Area();
        assertThat(area1).isNotEqualTo(area2);

        area2.setId(area1.getId());
        assertThat(area1).isEqualTo(area2);

        area2 = getAreaSample2();
        assertThat(area1).isNotEqualTo(area2);
    }

    @Test
    void employeeTest() throws Exception {
        Area area = getAreaRandomSampleGenerator();
        Employee employeeBack = getEmployeeRandomSampleGenerator();

        area.addEmployee(employeeBack);
        assertThat(area.getEmployees()).containsOnly(employeeBack);

        area.removeEmployee(employeeBack);
        assertThat(area.getEmployees()).doesNotContain(employeeBack);

        area.employees(new HashSet<>(Set.of(employeeBack)));
        assertThat(area.getEmployees()).containsOnly(employeeBack);

        area.setEmployees(new HashSet<>());
        assertThat(area.getEmployees()).doesNotContain(employeeBack);
    }
}
