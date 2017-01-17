
package com.example.simple.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by guozhk on 16-8-5.
 */
public class StringCheckUtil {
   /* private static final String CHECK_PHONE = "^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$";
    private static final String CHECK_IP = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
   // private static final String CHECK_PEOPLE_CODE_18 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$";
    private static final String CHECK_PEOPLE_CODE_18 = "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
   // private static final String CHECK_PEOPLE_CODE_15 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
    private static final String CHECK_PEOPLE_CODE_15 = "^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$";

*/

    private static final String CHECK_PHONE = "^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$";
    private static final String CHECK_IP = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
    // private static final String CHECK_PEOPLE_CODE_18 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$";
    // private static final String CHECK_PEOPLE_CODE_18 = "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
    private static final String CHECK_PEOPLE_CODE_18 = "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
    // private static final String CHECK_PEOPLE_CODE_15 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
    private static final String CHECK_PEOPLE_CODE_15 = "^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}";



    /**
     * 正则表达式:验证密码(不包含特殊字符) 6~11
     */
    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,11}$";



    /**
     * 判断字符串非空
     *
     * @param str
     * @return boolean
     */
    public static boolean checkEmpty(String str) {
        if (str == null) {
            return false;
        }
        if ("".equals(str)) {
            return false;
        }
        return true;
    }


    /**
     * 检验手机号码
     *
     * @param str str
     * @return boolean
     */
    public static Boolean checkPhone(String str) {
        if (!checkEmpty(str)) {
            return false;
        }
        return checkRegex(str, CHECK_PHONE);

    }

    /**
     * 判断IP格式和范围
     *
     * @param addr addr
     * @return boolean
     */
    public static boolean isIP(String addr) {
        if (!checkEmpty(addr)) {
            return false;
        }
        if (addr.length() < 7 || addr.length() > 15 || "".equals(addr)) {
            return false;
        }
        return checkRegex(addr, CHECK_IP);

    }

    /**
     * 检验身份证号码
     *
     * @param code code
     * @return boolean
     */
    public static boolean isPeopleCode(String code) {
        if (!checkEmpty(code)) {
            return false;
        }
        if (code.length() == 15) {
            return checkRegex(code, CHECK_PEOPLE_CODE_15);
        }
        if (code.length() == 18) {
            return checkRegex(code, CHECK_PEOPLE_CODE_18);
        }

        return false;

    }


    public static Boolean checkPWD(String str) {
        if (!checkEmpty(str)) {
            return false;
        }
        return checkRegex(str, REGEX_PASSWORD);

    }

    /**
     * 验证字符串
     *
     * @param str      str
     * @param regexstr regexstr
     * @return boolean
     */
    public static Boolean checkRegex(String str, String regexstr) {
        Pattern regex = Pattern.compile(regexstr);
        Matcher matcher = regex.matcher(str);
        return matcher.matches();
    }


    public static void main(String[] args){
        String s1="412726198502061236";
        String s2="41272619850206123X";
        String s21="41272619850206123x";
        String s3="320311770706001";
        String s4="320311770706004";
        String s41="32031177070600X";
        String s42="32031177070600x";

        System.out.println("s1:"+isPeopleCode(s1));
        System.out.println("s2:"+isPeopleCode(s2));
        System.out.println("s3:"+isPeopleCode(s3));
        System.out.println("s4:"+isPeopleCode(s4));
    }


}
