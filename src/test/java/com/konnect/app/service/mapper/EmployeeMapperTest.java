package com.konnect.app.service.mapper;

import org.junit.jupiter.api.BeforeEach;

class EmployeeMapperTest {

    private EmployeeMapper employeeMapper;

    @BeforeEach
    public void setUp() {
        employeeMapper = new EmployeeMapperImpl();
    }
}
