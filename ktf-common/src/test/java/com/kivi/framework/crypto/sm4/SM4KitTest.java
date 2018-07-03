package com.kivi.framework.crypto.sm4;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.kivi.framework.util.kit.HexKit;

public class SM4KitTest {

    private byte[] key = { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };

    @Test
    public void testEncrypt_ecb() throws Exception {
        byte[] plain = { 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08 };
        byte[] chipher = SM4Kit.encrypt_ecb(key, key);
        System.out.println("chiper:" + HexKit.binary2Hex(chipher));
    }

    @Test
    public void testEncrypt_ecb_hexByteArrayByteArray() {
        fail("Not yet implemented");
    }

    @Test
    public void testEncrypt_ecb_hexByteArrayString() {
        fail("Not yet implemented");
    }

    @Test
    public void testEncrypt_ecb_base64() {
        fail("Not yet implemented");
    }

    @Test
    public void testEncrypt_ecb_padding() {
        fail("Not yet implemented");
    }

    @Test
    public void testEncrypt_ecb_padding_hexByteArrayByteArray() {
        fail("Not yet implemented");
    }

    @Test
    public void testEncrypt_ecb_padding_hexByteArrayString() {
        fail("Not yet implemented");
    }

    @Test
    public void testEncrypt_ecb_padding_base64() {
        fail("Not yet implemented");
    }

    @Test
    public void testDecrypt_ecb() {
        fail("Not yet implemented");
    }

    @Test
    public void testDecrypt_ecb_hex() throws Exception {

        String chiper = "9F1F7BFF6F5511384D9430531E538FD3";

        byte[] plain = SM4Kit.decrypt_ecb(key, HexKit.hex2Byte(chiper));

        System.out.println("plain:" + HexKit.binary2Hex(plain));
    }

    @Test
    public void testDecrypt_ecb_base64() {
        fail("Not yet implemented");
    }

    @Test
    public void testDecrypt_ecb_padding() {
        fail("Not yet implemented");
    }

    @Test
    public void testDecrypt_ecb_padding_hex() {
        fail("Not yet implemented");
    }

    @Test
    public void testDecrypt_ecb_padding_base64() {
        fail("Not yet implemented");
    }

    @Test
    public void testEncrypt_cbc() {
        fail("Not yet implemented");
    }

    @Test
    public void testEncrypt_cbc_hex() {
        fail("Not yet implemented");
    }

    @Test
    public void testEncrypt_cbc_base64() {
        fail("Not yet implemented");
    }

    @Test
    public void testEncrypt_cbc_padding() {
        fail("Not yet implemented");
    }

    @Test
    public void testEncrypt_cbc_padding_hex() {
        fail("Not yet implemented");
    }

    @Test
    public void testEncrypt_cbc_padding_base64() {
        fail("Not yet implemented");
    }

    @Test
    public void testDecrypt_cbc() {
        fail("Not yet implemented");
    }

    @Test
    public void testDecrypt_cbc_hex() {
        fail("Not yet implemented");
    }

    @Test
    public void testDecrypt_cbc_base64() {
        fail("Not yet implemented");
    }

    @Test
    public void testDecrypt_cbc_padding() {
        fail("Not yet implemented");
    }

    @Test
    public void testDecrypt_cbc_padding_hex() {
        fail("Not yet implemented");
    }

    @Test
    public void testDecrypt_cbc_padding_base64() {
        fail("Not yet implemented");
    }

}
