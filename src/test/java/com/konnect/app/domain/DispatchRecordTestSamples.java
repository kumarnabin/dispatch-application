package com.konnect.app.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class DispatchRecordTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static DispatchRecord getDispatchRecordSample1() {
        return new DispatchRecord().id(1L).remark("remark1");
    }

    public static DispatchRecord getDispatchRecordSample2() {
        return new DispatchRecord().id(2L).remark("remark2");
    }

    public static DispatchRecord getDispatchRecordRandomSampleGenerator() {
        return new DispatchRecord().id(longCount.incrementAndGet()).remark(UUID.randomUUID().toString());
    }
}
