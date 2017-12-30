package com.example.demo.util;

import java.math.BigDecimal;

/**
 * @author jh
 * @date 2017/12/8 19:46
 */
public class CommUtil {

    /**
     * object 转换为Long
     *
     * @param s object
     * @return Long
     */
    public static Long null2Long(Object s) {
        Long v = -1L;
        if (s != null)
            try {
                v = Long.parseLong(s.toString());
            } catch (Exception localException) {
            }
        return v;
    }

    /**
     * 将obj转为String, 默认为""
     *
     * @param s Object
     * @return String
     */
    public static String null2String(Object s) {
        return s == null ? "" : s.toString().trim();
    }


    /**
     * 判断String是否为空
     *
     * @param s String
     * @return boolean
     */
    public static Boolean isEmptyString(String s) {
        if (s == null || s.length() == 0 || s.trim().length() == 0) {
            return true;
        }
        return false;
    }


    /**
     * String 转换为BigDecimal
     *
     * @param s string
     * @return BigDecimal
     */
    public static BigDecimal string2Decimal(String s) {
        BigDecimal bd = null;
        if (!isEmptyString(s)) {
            try {
                bd = new BigDecimal(s);
                bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return bd;
    }

}
