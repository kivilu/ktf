package com.kivi.framework.util.kit;

import static org.junit.Assert.assertEquals;

import java.nio.ByteBuffer;

import org.junit.Before;
import org.junit.Test;

public class DateTimeKitTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testCurrentUnix() {
        long unixTime = DateTimeKit.currentUnix();
        System.out.println(unixTime);
        String hexUnixTime = Long.toHexString(unixTime);
        System.out.println(hexUnixTime);

        int intTime = (int) unixTime;
        System.out.println(intTime);

        ByteBuffer buff = ByteBuffer.allocate(4);
        buff.putInt(intTime);
        System.out.println(ByteStringKit.toString(buff.array(), ByteStringKit.HEX));
        buff.rewind();
        long longTime = buff.getInt();
        System.out.println(longTime);

        String sdate = DateTimeKit.formatDateTime(DateTimeKit.unixTime((int) unixTime));
        System.out.println(sdate);
        String newdate = DateTimeKit.formatDateTime(DateTimeKit.unixTime(intTime));
        System.out.println(newdate);

        assertEquals(sdate, newdate);
    }

    @Test
    public void testToIntSecond() {
        int seconds = DateTimeKit.toIntSecond(DateTimeKit.date());

        System.out.println(seconds);
    }

}
