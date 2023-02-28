package com.lhk.utils;



import org.junit.Test;

import java.util.UUID;

/**
 * @author TheMutents
 * @creat on 2021-12-09-0:16
 */

public class IDUtils {

    public static String getId(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    @Test
    public void testUUID(){
        System.out.println(UUID.randomUUID().toString().replaceAll("-",""));

        System.out.println(getId());
        System.out.println(getId());
        System.out.println(getId());
    }
}

