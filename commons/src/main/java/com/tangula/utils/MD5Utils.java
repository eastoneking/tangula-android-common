package com.tangula.utils;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

public class MD5Utils {
    public  static String md5(String s) {

        return new String(Hex.encodeHex(DigestUtils.md5(s)));

    }
}


