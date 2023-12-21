package com.konnect.app.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class TeamTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Team getTeamSample1() {
        return new Team()
            .id(1L)
            .teamNo("teamNo1")
            .supervisor("supervisor1")
            .supervisorPhoneNo("supervisorPhoneNo1")
            .teamLeader("teamLeader1")
            .teamLeaderPhone("teamLeaderPhone1");
    }

    public static Team getTeamSample2() {
        return new Team()
            .id(2L)
            .teamNo("teamNo2")
            .supervisor("supervisor2")
            .supervisorPhoneNo("supervisorPhoneNo2")
            .teamLeader("teamLeader2")
            .teamLeaderPhone("teamLeaderPhone2");
    }

    public static Team getTeamRandomSampleGenerator() {
        return new Team()
            .id(longCount.incrementAndGet())
            .teamNo(UUID.randomUUID().toString())
            .supervisor(UUID.randomUUID().toString())
            .supervisorPhoneNo(UUID.randomUUID().toString())
            .teamLeader(UUID.randomUUID().toString())
            .teamLeaderPhone(UUID.randomUUID().toString());
    }
}
