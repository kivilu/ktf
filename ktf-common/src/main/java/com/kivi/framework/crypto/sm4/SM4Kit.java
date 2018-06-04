/**
 * 
 */
package com.kivi.framework.crypto.sm4;

import com.kivi.framework.util.Convert;

import jodd.util.Base64;

/**
 * @author gx
 *
 */
public class SM4Kit {

    private static final String            CHARSET_UTF8    = "UTF-8";

    private static ThreadLocal<SM4Context> ctxsNoPadding   = new ThreadLocal<SM4Context>() {

                                                               @Override
                                                               protected SM4Context initialValue() {
                                                                   return new SM4Context(false);
                                                               }

                                                           };

    private static ThreadLocal<SM4Context> ctxsWithPadding = new ThreadLocal<SM4Context>() {

                                                               @Override
                                                               protected SM4Context initialValue() {
                                                                   return new SM4Context();
                                                               }

                                                           };

    private static ThreadLocal<SM4>        sm4Inst         = new ThreadLocal<SM4>() {

                                                               @Override
                                                               protected SM4 initialValue() {
                                                                   return new SM4();
                                                               }

                                                           };

    /**
     * SM4 ecb加密
     * 
     * @param key
     * @param input
     * @return
     * @throws Exception
     */
    public static byte[] encrypt_ecb( byte[] key, byte[] input ) throws Exception {

        if (input.length % 16 != 0) {
            throw new Exception("输入数据长度不是16的倍数");
        }

        SM4Context ctx = ctxsNoPadding.get();
        SM4 sm4 = sm4Inst.get();
        sm4.sm4_setkey_enc(ctx, key);

        return sm4.sm4_crypt_ecb_V2(ctx, input);
    }

    /**
     * SM4 ecb加密
     * 
     * @param key
     * @param input
     * @return
     * @throws Exception
     */
    public static String encrypt_ecb_hex( byte[] key, byte[] input ) throws Exception {

        return Convert.toHex(encrypt_ecb(key, input));
    }

    /**
     * SM4 ecb加密
     * 
     * @param key
     * @param input
     * @return
     * @throws Exception
     */
    public static String encrypt_ecb_hex( byte[] key, String input ) throws Exception {

        return encrypt_ecb_hex(key, input.getBytes(CHARSET_UTF8));
    }

    /**
     * SM4 ecb加密
     * 
     * @param key
     * @param input
     * @return
     * @throws Exception
     */
    public static String encrypt_ecb_base64( byte[] key, byte[] input ) throws Exception {

        return Base64.encodeToString(encrypt_ecb(key, input));
    }

    /**
     * SM4 ecb带填充方式加密，
     * 
     * @param key
     * @param input
     * @return
     * @throws Exception
     */
    public static byte[] encrypt_ecb_padding( byte[] key, byte[] input ) throws Exception {
        SM4Context ctx = ctxsWithPadding.get();
        SM4 sm4 = sm4Inst.get();
        sm4.sm4_setkey_enc(ctx, key);

        return sm4.sm4_crypt_ecb_V2(ctx, input);
    }

    /**
     * SM4 ecb带填充方式加密，
     * 
     * @param key
     * @param input
     * @return
     * @throws Exception
     */
    public static String encrypt_ecb_padding_hex( byte[] key, byte[] input ) throws Exception {

        return Convert.toHex(encrypt_ecb_padding(key, input));
    }

    /**
     * SM4 ecb带填充方式加密，
     * 
     * @param key
     * @param input
     * @return
     * @throws Exception
     */
    public static String encrypt_ecb_padding_hex( byte[] key, String input ) throws Exception {

        return encrypt_ecb_padding_hex(key, input.getBytes(CHARSET_UTF8));
    }

    /**
     * SM4 ecb带填充方式加密，
     * 
     * @param key
     * @param input
     * @return
     * @throws Exception
     */
    public static String encrypt_ecb_padding_base64( byte[] key, byte[] input ) throws Exception {

        return Base64.encodeToString(encrypt_ecb_padding(key, input));
    }

    /**
     * SM4 ecb解密
     * 
     * @param key
     * @param input
     * @return
     * @throws Exception
     */
    public static byte[] decrypt_ecb( byte[] key, byte[] input ) throws Exception {

        if (input.length % 16 != 0) {
            throw new Exception("输入数据长度不是16的倍数");
        }

        SM4Context ctx = ctxsNoPadding.get();
        SM4 sm4 = sm4Inst.get();
        sm4.sm4_setkey_dec(ctx, key);

        return sm4.sm4_crypt_ecb_V2(ctx, input);
    }

    /**
     * SM4 ecb解密
     * 
     * @param key
     * @param input
     * @return
     * @throws Exception
     */
    public static String decrypt_ecb_hex( byte[] key, byte[] input ) throws Exception {

        return Convert.toHex(decrypt_ecb(key, input));
    }

    /**
     * SM4 ecb解密
     * 
     * @param key
     * @param input
     * @return
     * @throws Exception
     */
    public static String decrypt_ecb_base64( byte[] key, byte[] input ) throws Exception {

        return Base64.encodeToString(decrypt_ecb(key, input));
    }

    /**
     * SM4 ecb带填充方式解密
     * 
     * @param key
     * @param input
     * @return
     * @throws Exception
     */
    public static byte[] decrypt_ecb_padding( byte[] key, byte[] input ) throws Exception {
        SM4Context ctx = ctxsWithPadding.get();
        SM4 sm4 = sm4Inst.get();
        sm4.sm4_setkey_dec(ctx, key);

        return sm4.sm4_crypt_ecb_V2(ctx, input);
    }

    /**
     * SM4 ecb带填充方式解密
     * 
     * @param key
     * @param input
     * @return
     * @throws Exception
     */
    public static String decrypt_ecb_padding_hex( byte[] key, byte[] input ) throws Exception {

        return Convert.toHex(decrypt_ecb_padding(key, input));
    }

    /**
     * SM4 ecb带填充方式解密
     * 
     * @param key
     * @param input
     * @return
     * @throws Exception
     */
    public static String decrypt_ecb_padding_base64( byte[] key, byte[] input ) throws Exception {

        return Base64.encodeToString(decrypt_ecb_padding(key, input));
    }

    /**
     * SM4 cbc加密
     * 
     * @param key
     * @param iv
     * @param input
     * @return
     * @throws Exception
     */
    public static byte[] encrypt_cbc( byte[] key, byte[] iv, byte[] input ) throws Exception {
        if (input.length % 16 != 0) {
            throw new Exception("输入数据长度不是16的倍数");
        }

        SM4Context ctx = ctxsNoPadding.get();
        SM4 sm4 = sm4Inst.get();
        sm4.sm4_setkey_enc(ctx, key);

        return sm4.sm4_crypt_cbc_V2(ctx, iv, input);
    }

    /**
     * SM4 cbc加密
     * 
     * @param key
     * @param iv
     * @param input
     * @return
     * @throws Exception
     */
    public static String encrypt_cbc_hex( byte[] key, byte[] iv, byte[] input ) throws Exception {

        return Convert.toHex(encrypt_cbc(key, iv, input));
    }

    /**
     * SM4 cbc加密
     * 
     * @param key
     * @param iv
     * @param input
     * @return
     * @throws Exception
     */
    public static String encrypt_cbc_base64( byte[] key, byte[] iv, byte[] input ) throws Exception {

        return Base64.encodeToString(encrypt_cbc(key, iv, input));
    }

    /**
     * SM4 cbc带填充方式加密
     * 
     * @param key
     * @param iv
     * @param input
     * @return
     * @throws Exception
     */
    public static byte[] encrypt_cbc_padding( byte[] key, byte[] iv, byte[] input ) throws Exception {
        SM4Context ctx = ctxsWithPadding.get();
        SM4 sm4 = sm4Inst.get();
        sm4.sm4_setkey_enc(ctx, key);

        return sm4.sm4_crypt_cbc_V2(ctx, iv, input);
    }

    /**
     * SM4 cbc带填充方式加密
     * 
     * @param key
     * @param iv
     * @param input
     * @return
     * @throws Exception
     */
    public static String encrypt_cbc_padding_hex( byte[] key, byte[] iv, byte[] input ) throws Exception {

        return Convert.toHex(encrypt_cbc_padding(key, iv, input));
    }

    /**
     * SM4 cbc带填充方式加密
     * 
     * @param key
     * @param iv
     * @param input
     * @return
     * @throws Exception
     */
    public static String encrypt_cbc_padding_base64( byte[] key, byte[] iv, byte[] input ) throws Exception {

        return Base64.encodeToString(encrypt_cbc_padding(key, iv, input));
    }

    /**
     * SM4 cbc解密
     * 
     * @param key
     * @param input
     * @return
     * @throws Exception
     */
    public static byte[] decrypt_cbc( byte[] key, byte[] iv, byte[] input ) throws Exception {
        if (input.length % 16 != 0) {
            throw new Exception("输入数据长度不是16的倍数");
        }

        SM4Context ctx = ctxsNoPadding.get();
        SM4 sm4 = sm4Inst.get();
        sm4.sm4_setkey_dec(ctx, key);

        return sm4.sm4_crypt_cbc_V2(ctx, iv, input);
    }

    /**
     * SM4 cbc解密
     * 
     * @param key
     * @param input
     * @return
     * @throws Exception
     */
    public static String decrypt_cbc_hex( byte[] key, byte[] iv, byte[] input ) throws Exception {

        return Convert.toHex(decrypt_cbc(key, iv, input));
    }

    /**
     * SM4 cbc解密
     * 
     * @param key
     * @param input
     * @return
     * @throws Exception
     */
    public static String decrypt_cbc_base64( byte[] key, byte[] iv, byte[] input ) throws Exception {

        return Base64.encodeToString(decrypt_cbc(key, iv, input));
    }

    /**
     * SM4 cbc带填充方式解密
     * 
     * @param key
     * @param input
     * @return
     * @throws Exception
     */
    public static byte[] decrypt_cbc_padding( byte[] key, byte[] iv, byte[] input ) throws Exception {
        SM4Context ctx = ctxsWithPadding.get();
        SM4 sm4 = sm4Inst.get();
        sm4.sm4_setkey_dec(ctx, key);

        return sm4.sm4_crypt_cbc_V2(ctx, iv, input);
    }

    /**
     * SM4 cbc带填充方式解密
     * 
     * @param key
     * @param input
     * @return
     * @throws Exception
     */
    public static String decrypt_cbc_padding_hex( byte[] key, byte[] iv, byte[] input ) throws Exception {

        return Convert.toHex(decrypt_cbc_padding(key, iv, input));
    }

    /**
     * SM4 cbc带填充方式解密
     * 
     * @param key
     * @param input
     * @return
     * @throws Exception
     */
    public static String decrypt_cbc_padding_base64( byte[] key, byte[] iv, byte[] input ) throws Exception {

        return Base64.encodeToString(decrypt_cbc_padding(key, iv, input));
    }
}
