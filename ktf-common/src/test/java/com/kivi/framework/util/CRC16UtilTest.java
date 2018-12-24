package com.kivi.framework.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.kivi.framework.util.kit.HexKit;

public class CRC16UtilTest {

    @Test
    public void testCalcCrc16ByteArray() {
        int crc = CRC16Util.calcCrc16(HexKit.hex2Byte("02AB0133F081002800000902AB31AABBCCDDEEFF"));
        assertEquals(0x1675, crc);

        crc = CRC16Util.calcCrc16(HexKit.hex2Byte("AB0133F081002800000902AB31AABBCCDDEEFF"));
        assertEquals(0xcb0c, crc);
    }

}
