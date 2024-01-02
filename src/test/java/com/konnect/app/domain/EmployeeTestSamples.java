package com.konnect.app.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class EmployeeTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Employee getEmployeeSample1() {
        return new Employee()
            .id(1L)
            .fullName("fullName1")
            .gender("gender1")
            .mobile("mobile1")
            .citizenshipNo("citizenshipNo1")
            .panNo("panNo1")
            .category("category1")
            .detail("detail1");
    }

    public static Employee getEmployeeSample2() {
        return new Employee()
            .id(2L)
            .fullName("fullName2")
            .gender("gender2")
            .mobile("mobile2")
            .citizenshipNo("citizenshipNo2")
            .panNo("panNo2")
            .category("category2")
            .detail("detail2");
    }

    public static Employee getEmployeeRandomSampleGenerator() {
        return new Employee()
            .id(longCount.incrementAndGet())
            .fullName(UUID.randomUUID().toString())
            .gender(UUID.randomUUID().toString())
            .mobile(UUID.randomUUID().toString())
            .citizenshipNo(UUID.randomUUID().toString())
            .panNo(UUID.randomUUID().toString())
            .category(UUID.randomUUID().toString())
            .detail(UUID.randomUUID().toString());
    }
}
