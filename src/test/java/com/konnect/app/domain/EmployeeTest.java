package com.konnect.app.domain;

import static com.konnect.app.domain.AreaTestSamples.*;
import static com.konnect.app.domain.EmployeeTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.konnect.app.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class EmployeeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Employee.class);
        Employee employee1 = getEmployeeSample1();
        Employee employee2 = new Employee();
        assertThat(employee1).isNotEqualTo(employee2);

        employee2.setId(employee1.getId());
        assertThat(employee1).isEqualTo(employee2);

        employee2 = getEmployeeSample2();
        assertThat(employee1).isNotEqualTo(employee2);
    }

    @Test
    void areaTest() throws Exception {
        Employee employee = getEmployeeRandomSampleGenerator();
        Area areaBack = getAreaRandomSampleGenerator();

        employee.addArea(areaBack);
        assertThat(employee.getAreas()).containsOnly(areaBack);
        assertThat(areaBack.getEmployees()).containsOnly(employee);

        employee.removeArea(areaBack);
        assertThat(employee.getAreas()).doesNotContain(areaBack);
        assertThat(areaBack.getEmployees()).doesNotContain(employee);

        employee.areas(new HashSet<>(Set.of(areaBack)));
        assertThat(employee.getAreas()).containsOnly(areaBack);
        assertThat(areaBack.getEmployees()).containsOnly(employee);

        employee.setAreas(new HashSet<>());
        assertThat(employee.getAreas()).doesNotContain(areaBack);
        assertThat(areaBack.getEmployees()).doesNotContain(employee);
    }
}
