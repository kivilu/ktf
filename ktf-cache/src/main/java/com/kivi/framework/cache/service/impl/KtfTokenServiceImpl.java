package com.kivi.framework.cache.service.impl;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.kivi.framework.cache.constant.KtfCache;
import com.kivi.framework.cache.service.KtfTokenService;
import com.kivi.framework.util.kit.CollectionKit;
import com.kivi.framework.util.kit.StrKit;

@Service
public class KtfTokenServiceImpl implements KtfTokenService {

    @Override
    public String nonce( Object... seeds ) {
        return generate(seeds);
    }

    @Override
    public String token( Object... seeds ) {
        return generate(seeds);
    }

    @CachePut( value = KtfCache.KTF_TOKEN, key = "caches[0].name+'_'+#name+#key" )
    @Override
    public <T> T cache( String name, String key, T obj ) {
        return obj;
    }

    @Cacheable( value = KtfCache.KTF_TOKEN, key = "caches[0].name+'_'+#name+#key" )
    @Override
    public <T> T cache( String name, String key ) {
        return null;
    }

    private String generate( Object[] seeds ) {
        StringBuilder plain = StrKit.builder();

        List<Object> datas = CollectionKit.newArrayList(seeds);
        datas.forEach(data-> plain.append(data));

        return DigestUtils.md5Hex(plain.toString());
    }

}
