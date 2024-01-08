package com.konnect.app.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class OltTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Olt getOltSample1() {
        return new Olt().id(1L).name("name1").detail("detail1");
    }

    public static Olt getOltSample2() {
        return new Olt().id(2L).name("name2").detail("detail2");
    }

    public static Olt getOltRandomSampleGenerator() {
        return new Olt().id(longCount.incrementAndGet()).name(UUID.randomUUID().toString()).detail(UUID.randomUUID().toString());
    }
}
