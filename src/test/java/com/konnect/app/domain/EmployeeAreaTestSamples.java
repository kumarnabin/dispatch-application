package com.konnect.app.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class EmployeeAreaTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static EmployeeArea getEmployeeAreaSample1() {
        return new EmployeeArea().id(1L);
    }

    public static EmployeeArea getEmployeeAreaSample2() {
        return new EmployeeArea().id(2L);
    }

    public static EmployeeArea getEmployeeAreaRandomSampleGenerator() {
        return new EmployeeArea().id(longCount.incrementAndGet());
    }
}
