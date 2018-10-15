package com.tangula.utils

import java.util.regex.Pattern

/**
 * 正则工具.
 */
object PatternUtils {

    const val PATTERN_EMAIL = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*"
    const val PATTERN_MOBILE_NO = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$"


    /**
     * 判断输入是否为手机号.
     * @param [input] 被检测字符串.
     * @return 检测结果.
     */
    fun isMobileNO(input: String): Boolean {
        return isMatchAPattenAndNotNull(input, PATTERN_MOBILE_NO)
    }

    /**
     * 判断邮箱是否合法.
     * @param [input] 被检测字符串.
     * @return 检测结果.
     */
    fun isEmail(input: String?): Boolean {
        return isMatchAPattenAndNotNull(input, PATTERN_EMAIL)
    }

    /**
     * 检测字符串是否非空并且符合指定的正则表达式.
     * @param [input] 被检测字符串.
     * @param [pattern] 用于检测的正则表达式.
     * @return 检测结果.
     */
    fun isMatchAPattenAndNotNull(input:String?, pattern:String):Boolean{
        return input?.let {
            Pattern.compile(pattern).matcher(input).matches()
        }?:false
    }

}
