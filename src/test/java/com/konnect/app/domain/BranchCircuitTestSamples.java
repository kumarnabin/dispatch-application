package com.konnect.app.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class BranchCircuitTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static BranchCircuit getBranchCircuitSample1() {
        return new BranchCircuit().id(1L).title("title1");
    }

    public static BranchCircuit getBranchCircuitSample2() {
        return new BranchCircuit().id(2L).title("title2");
    }

    public static BranchCircuit getBranchCircuitRandomSampleGenerator() {
        return new BranchCircuit().id(longCount.incrementAndGet()).title(UUID.randomUUID().toString());
    }
}
