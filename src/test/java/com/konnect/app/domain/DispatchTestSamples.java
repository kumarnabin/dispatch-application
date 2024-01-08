package com.konnect.app.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class DispatchTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Dispatch getDispatchSample1() {
        return new Dispatch()
            .id(1L)
            .voice("voice1")
            .data("data1")
            .iptv("iptv1")
            .customerName("customerName1")
            .contactNo("contactNo1")
            .oltPort("oltPort1")
            .regDate("regDate1")
            .fapPort("fapPort1")
            .cpeSn("cpeSn1")
            .cpeRx("cpeRx1")
            .complain("complain1")
            .remark("remark1")
            .location("location1");
    }

    public static Dispatch getDispatchSample2() {
        return new Dispatch()
            .id(2L)
            .voice("voice2")
            .data("data2")
            .iptv("iptv2")
            .customerName("customerName2")
            .contactNo("contactNo2")
            .oltPort("oltPort2")
            .regDate("regDate2")
            .fapPort("fapPort2")
            .cpeSn("cpeSn2")
            .cpeRx("cpeRx2")
            .complain("complain2")
            .remark("remark2")
            .location("location2");
    }

    public static Dispatch getDispatchRandomSampleGenerator() {
        return new Dispatch()
            .id(longCount.incrementAndGet())
            .voice(UUID.randomUUID().toString())
            .data(UUID.randomUUID().toString())
            .iptv(UUID.randomUUID().toString())
            .customerName(UUID.randomUUID().toString())
            .contactNo(UUID.randomUUID().toString())
            .oltPort(UUID.randomUUID().toString())
            .regDate(UUID.randomUUID().toString())
            .fapPort(UUID.randomUUID().toString())
            .cpeSn(UUID.randomUUID().toString())
            .cpeRx(UUID.randomUUID().toString())
            .complain(UUID.randomUUID().toString())
            .remark(UUID.randomUUID().toString())
            .location(UUID.randomUUID().toString());
    }
}
