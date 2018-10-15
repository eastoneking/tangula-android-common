package com.tangula.utils;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * This is md5 utils.
 * <p>Because apache commons's md5 utils is not work well in android.简单的说，就是android自己带了
 * 一个apache commons库中的类，而android中自带的版本并不是依赖包中的版本，md5处理的这个方法缺失，
 * 而且app中调用的是android自带的版本，所以需要通过这个工具类包装一下android自带版本的接口，实现字符
 * 串的md5编码。这个工具类目前自由者一个功能。</p>
 */
public class MD5Utils {
    public  static String md5(String s) {
        return new String(Hex.encodeHex(DigestUtils.md5(s)));
    }
}


