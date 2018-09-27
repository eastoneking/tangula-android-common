package com.tangula.utils;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * This is md5 utils.
 * <p>because the apache commons md5 utils is not work well in android.</p>
 */
public class MD5Utils {
    public  static String md5(String s) {
        return new String(Hex.encodeHex(DigestUtils.md5(s)));
    }
}


