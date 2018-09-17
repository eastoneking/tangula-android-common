package com.tangula.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Md5UtilsTest {

    @Test
    public void testMd5(){
        assertEquals(MD5Utils.md5("1"), "c4ca4238a0b923820dcc509a6f75849b");
    }

}
