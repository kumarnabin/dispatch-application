package com.konnect.app.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class MasterCircuitTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static MasterCircuit getMasterCircuitSample1() {
        return new MasterCircuit().id(1L).name("name1").address("address1");
    }

    public static MasterCircuit getMasterCircuitSample2() {
        return new MasterCircuit().id(2L).name("name2").address("address2");
    }

    public static MasterCircuit getMasterCircuitRandomSampleGenerator() {
        return new MasterCircuit().id(longCount.incrementAndGet()).name(UUID.randomUUID().toString()).address(UUID.randomUUID().toString());
    }
}
