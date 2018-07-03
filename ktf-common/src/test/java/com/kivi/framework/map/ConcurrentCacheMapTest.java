package com.kivi.framework.map;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import com.kivi.framework.util.kit.StrKit;

public class ConcurrentCacheMapTest {

    public static void main( String[] args ) {
        ConcurrentCacheMap<String, Integer> map = new ConcurrentCacheMap<>(60, 1, 30, TimeUnit.SECONDS);

        for (int i = 0; i < 10; i++) {
            map.put(StrKit.join("-", "key", i), i);
            System.out.println("添加元素：" + i);
            LockSupport.parkNanos(1000000000);
        }

        while (true) {
            if (map.count() == 0)
                break;

            LockSupport.parkNanos(1000000000);
        }

        map.stop();

    }

}
