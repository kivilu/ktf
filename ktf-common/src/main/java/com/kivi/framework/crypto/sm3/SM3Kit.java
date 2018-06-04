/**
 * 
 */
package com.kivi.framework.crypto.sm3;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import com.kivi.framework.util.Convert;

import jodd.util.Base64;

/**
 * @author gx
 *
 */
public class SM3Kit {
    /** SM3值的长度 */
    private static final int           SM3_LENGTH    = 32;

    /** SM3分组长度 */
    private static final int           BLOCK_LENGTH  = 64;

    /** 缓冲区长度 */
    private static final int           BUFFER_LENGTH = BLOCK_LENGTH * 1;

    /** 缓冲区 */
    private ByteBuffer                 xBuf          = ByteBuffer.allocate(BUFFER_LENGTH);

    /** 初始向量 */
    private ByteBuffer                 V             = ByteBuffer.wrap(SM3.iv.clone());

    private int                        cntBlock      = 0;

    private static ThreadLocal<SM3Kit> INSTANCES     = new ThreadLocal<SM3Kit>() {

                                                         @Override
                                                         protected SM3Kit initialValue() {
                                                             return new SM3Kit();
                                                         }

                                                     };

    public static String sm3Hex( byte[] data ) {
        return Convert.toHex(sm3(data));
    }

    public static String sm3Hex( String data ) {
        return Convert.toHex(sm3(data));
    }

    public static String sm3Base64( byte[] data ) {
        return Base64.encodeToString(sm3(data));
    }

    public static String sm3Base64( String data ) {
        return Base64.encodeToString(sm3(data));
    }

    public static byte[] sm3( byte[] data ) {
        byte[] sm3 = new byte[SM3_LENGTH];
        SM3Kit sm3Digest = INSTANCES.get();
        sm3Digest.update(data, 0, data.length);
        sm3Digest.doFinal(sm3, 0);

        return sm3;
    }

    public static byte[] sm3( String data ) {
        byte[] bdata;
        try {
            bdata = data.getBytes("UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

        return sm3(bdata);
    }

    public SM3Kit() {
    }

    public SM3Kit( SM3Kit t ) {
        xBuf = t.xBuf.duplicate();
        V = t.V.duplicate();
    }

    /**
     * SM3结果输出
     * 
     * @param out
     *            保存SM3结构的缓冲区
     * @param outOff
     *            缓冲区偏移量
     * @return
     */
    public int doFinal( byte[] out, int outOff ) {
        byte[] tmp = doFinal();
        System.arraycopy(tmp, 0, out, 0, tmp.length);

        reset();
        return SM3_LENGTH;
    }

    public void reset() {
        // xBufOff = 0;
        cntBlock = 0;

        xBuf.clear();
        V.clear();
        V.put(SM3.iv.clone());
    }

    /**
     * 明文输入
     * 
     * @param in
     *            明文输入缓冲区
     * @param inOff
     *            缓冲区偏移量
     * @param len
     *            明文长度
     */
    public void update( byte[] in, int inOff, int len ) {
        int partLen = BUFFER_LENGTH - xBuf.position();
        int inputLen = len;
        int dPos = inOff;
        if (partLen < inputLen) {
            // System.arraycopy(in, dPos, xBuf, xBufOff, partLen);
            xBuf.put(in, dPos, partLen);
            inputLen -= partLen;
            dPos += partLen;
            doUpdate();
            while (inputLen > BUFFER_LENGTH) {
                // System.arraycopy(in, dPos, xBuf, 0, BUFFER_LENGTH);
                xBuf.put(in, dPos, BUFFER_LENGTH);
                inputLen -= BUFFER_LENGTH;
                dPos += BUFFER_LENGTH;
                doUpdate();
            }
        }

        xBuf.put(in, dPos, inputLen);
        // System.arraycopy(in, dPos, xBuf, xBufOff, inputLen);
        // xBufOff += inputLen;
    }

    private void doUpdate() {
        byte[] B = new byte[BLOCK_LENGTH];

        xBuf.rewind();
        for (int i = 0; i < BUFFER_LENGTH; i += BLOCK_LENGTH) {
            xBuf.get(B);
            // System.arraycopy(xBuf, i, B, 0, B.length);
            doHash(B);
        }
        xBuf.clear();
    }

    private void doHash( byte[] B ) {
        byte[] tmp = SM3.CF(V.array(), B);
        V.clear();
        V.put(tmp);
        cntBlock++;
    }

    private byte[] doFinal() {
        ByteBuffer B = ByteBuffer.allocate(BLOCK_LENGTH);
        byte[] buffer = new byte[xBuf.position()];
        xBuf.rewind();
        xBuf.get(buffer);
        byte[] tmp = SM3.padding(buffer, cntBlock);
        for (int i = 0; i < tmp.length; i += BLOCK_LENGTH) {
            B.put(tmp, i, BLOCK_LENGTH);
            doHash(B.array());
            B.clear();
        }
        return V.array();
    }

    public void update( byte in ) {
        byte[] buffer = new byte[] { in };
        update(buffer, 0, 1);
    }

    public int getDigestSize() {
        return SM3_LENGTH;
    }

}
