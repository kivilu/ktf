package com.kivi.framework.web.jwt;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import com.kivi.framework.util.kit.ByteStringKit;
import com.kivi.framework.util.kit.StrKit;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class JwtUser implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    // 用户iD
    @Builder.Default
    private Long              id               = 0L;

    // 用户标识
    @Builder.Default
    private String            identifier       = "";

    public String[] audience() {
        String[] result = new String[2];
        result[0] = id.toString();
        result[1] = encode(identifier);

        return result;
    }

    public static JwtUser audience( List<String> audiences ) {
        JwtUser result = JwtUser.builder()
                .id(Long.valueOf(audiences.get(0)))
                .identifier(decode(audiences.get(1)))
                .build();
        return result;
    }

    private static String encode( final String identifier ) {
        String data = padding(identifier);

        byte[] bdata = ByteStringKit.toBytes(data, ByteStringKit.HEX);
        ArrayUtils.reverse(bdata);

        return ByteStringKit.toString(bdata, ByteStringKit.BASE64);
    }

    private static String decode( final String identifier ) {
        byte[] rdata = ByteStringKit.toBytes(identifier, ByteStringKit.BASE64);
        ArrayUtils.reverse(rdata);

        String result = ByteStringKit.toString(rdata, ByteStringKit.HEX);
        int pads = Integer.parseInt(StrKit.subSuf(result, result.length() - 1));

        return result.substring(0, result.length() - pads);
    }

    private static String padding( final String identifier ) {
        final String[] pad = { "22", "1" };
        int mod = identifier.length() % 2;

        StringBuilder builder = new StringBuilder();
        builder.append(identifier);
        builder.append(pad[mod]);

        return builder.toString();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
                .append("id=").append(id).append(", ")
                .append("identifier").append(identifier);
        return builder.toString();
    }

}
