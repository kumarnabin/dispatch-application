package com.konnect.app.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ExcelDataTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static ExcelData getExcelDataSample1() {
        return new ExcelData()
            .id(1L)
            .column1("column11")
            .column2("column21")
            .column3("column31")
            .column4("column41")
            .column5("column51")
            .column6("column61")
            .column7("column71")
            .column8("column81")
            .column9("column91")
            .column10("column101")
            .column11("column111")
            .column12("column121")
            .column13("column131")
            .column14("column141")
            .column15("column151")
            .column16("column161")
            .column17("column171")
            .column18("column181")
            .column19("column191")
            .column20("column201")
            .column21("column211")
            .column22("column221")
            .column23("column231")
            .column24("column241")
            .column25("column251")
            .column26("column261")
            .column27("column271");
    }

    public static ExcelData getExcelDataSample2() {
        return new ExcelData()
            .id(2L)
            .column1("column12")
            .column2("column22")
            .column3("column32")
            .column4("column42")
            .column5("column52")
            .column6("column62")
            .column7("column72")
            .column8("column82")
            .column9("column92")
            .column10("column102")
            .column11("column112")
            .column12("column122")
            .column13("column132")
            .column14("column142")
            .column15("column152")
            .column16("column162")
            .column17("column172")
            .column18("column182")
            .column19("column192")
            .column20("column202")
            .column21("column212")
            .column22("column222")
            .column23("column232")
            .column24("column242")
            .column25("column252")
            .column26("column262")
            .column27("column272");
    }

    public static ExcelData getExcelDataRandomSampleGenerator() {
        return new ExcelData()
            .id(longCount.incrementAndGet())
            .column1(UUID.randomUUID().toString())
            .column2(UUID.randomUUID().toString())
            .column3(UUID.randomUUID().toString())
            .column4(UUID.randomUUID().toString())
            .column5(UUID.randomUUID().toString())
            .column6(UUID.randomUUID().toString())
            .column7(UUID.randomUUID().toString())
            .column8(UUID.randomUUID().toString())
            .column9(UUID.randomUUID().toString())
            .column10(UUID.randomUUID().toString())
            .column11(UUID.randomUUID().toString())
            .column12(UUID.randomUUID().toString())
            .column13(UUID.randomUUID().toString())
            .column14(UUID.randomUUID().toString())
            .column15(UUID.randomUUID().toString())
            .column16(UUID.randomUUID().toString())
            .column17(UUID.randomUUID().toString())
            .column18(UUID.randomUUID().toString())
            .column19(UUID.randomUUID().toString())
            .column20(UUID.randomUUID().toString())
            .column21(UUID.randomUUID().toString())
            .column22(UUID.randomUUID().toString())
            .column23(UUID.randomUUID().toString())
            .column24(UUID.randomUUID().toString())
            .column25(UUID.randomUUID().toString())
            .column26(UUID.randomUUID().toString())
            .column27(UUID.randomUUID().toString());
    }
}
