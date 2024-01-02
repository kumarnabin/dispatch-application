package com.konnect.app.service.mapper;

import org.junit.jupiter.api.BeforeEach;

class AttendanceMapperTest {

    private AttendanceMapper attendanceMapper;

    @BeforeEach
    public void setUp() {
        attendanceMapper = new AttendanceMapperImpl();
    }
}
