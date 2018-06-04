/**
 * 
 */
package com.kivi.framework.crypto.sm4;

/**
 * @author gx
 *
 */
public class SM4Context {
    public int     mode;

    public long[]  sk;

    public boolean isPadding;

    public SM4Context() {
        this.mode = 1;
        this.isPadding = true;
        this.sk = new long[32];
    }

    public SM4Context( boolean isPadding ) {
        this.mode = 1;
        this.isPadding = isPadding;
        this.sk = new long[32];
    }

}
