package com.echat.sdk.test.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by qjl on 2015/4/4.
 */
public class StringUtil {

    private static DecimalFormat decimalFormat = new DecimalFormat("0.00");

    private static final String zero = "0";

    private static final String one = "1";

    public static final String emptyStr = "";

    public static final String keySplit = ",";//分隔符

    public static boolean isNotEmpty(String str) {
        return str != null && !emptyStr.equals(str);
    }

    public static boolean isEmpty(String str) {
        return str == null || emptyStr.equals(str);
    }

    /**
     * @param st
     * @return 返回escape过后的字符串
     */
    public static String escapeAttributeEntities(String st) {
        if (st == null) {
            return null;
        }
        //StringBuffer 是同步和线程安全的，效率低，StringBuilder是不同步的，效率高
        StringBuilder buff = new StringBuilder();
        char block[] = st.toCharArray();
        String stEntity = null;
        int i = 0;
        int last = 0;
        for (; i < block.length; i++) {

            //使用空格替代非法字符.
            if (!isXMLCharacter(block[i])) {
                block[i] = (char) 0x20;
                continue;
            }

            switch (block[i]) {
                case 60: // '<'
                    stEntity = "&lt;";
                    break;

                case 62: // '>'
                    stEntity = "&gt;";
                    break;

                case 39: // '\''
                    stEntity = "&apos;";
                    break;

                case 34: // '"'
                    stEntity = "&quot;";
                    break;

                case 38: // '&'
                    stEntity = "&amp;";
                    break;
            }
            if (stEntity != null) {
                buff.append(block, last, i - last);
                buff.append(stEntity);
                stEntity = null;
                last = i + 1;
            }
        }

        if (last < block.length) {
            buff.append(block, last, i - last);
        }
        return buff.toString();
    }

    /**
     * @param st String
     * @return String
     */
    public static String escapeElementEntities(String st) {
        if (st == null) {
            return null;
        }
        //StringBuffer 是同步和线程安全的，效率低，StringBuilder是不同步的，效率高
        StringBuilder buff = new StringBuilder();
        char block[] = st.toCharArray();
        String stEntity = null;
        int i = 0;
        int last = 0;
        for (; i < block.length; i++) {

            //使用空格替代非法字符.
            if (!isXMLCharacter(block[i])) {
                block[i] = (char) 0x20;
                continue;
            }

            switch (block[i]) {
                case 60: // '<'
                    stEntity = "&lt;";
                    break;

                case 62: // '>'
                    stEntity = "&gt;";
                    break;

                case 38: // '&'
                    stEntity = "&amp;";
                    break;
            }
            if (stEntity != null) {
                buff.append(block, last, i - last);
                buff.append(stEntity);
                stEntity = null;
                last = i + 1;
            }
        }

        if (last < block.length) {
            buff.append(block, last, i - last);
        }
        return buff.toString();
    }

    private static boolean isXMLCharacter(int c) {
        if (c <= 0xD7FF) {
            if (c >= 0x20)
                return true;
            else
                return c == '\n' || c == '\r' || c == '\t';
        }
        return (c >= 0xE000 && c <= 0xFFFD) || (c >= 0x10000 && c <= 0x10FFFF);
    }

    /**
     * 首字母转大写
     *
     * @param str
     * @return
     */
    public static String firstToUpperCase(String str) {
        StringBuilder sb = new StringBuilder(str);
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        return sb.toString();
    }

    public static String listToString(List list, String separator) {
        if (list == null || list.size() == 0) {
            return null;
        }
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            Object obj = list.get(i);
            if (i != list.size() - 1) {
                buffer.append(obj).append(separator);
            } else {
                buffer.append(obj);
            }
        }
        return buffer.toString();
    }

    public static List stringToList(String source, char separator) {
        if (isEmpty(source)) {
            return null;
        }
        String[] arr = split(source, separator);
        List list = Arrays.asList(arr);
        return new ArrayList(list);
    }

    /**
     * 判断字符串是否相等
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equals(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equals(str2);
    }

    /**
     * 判断字符串转字节是否相等
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equals(String str1, byte str2) {
        return str1 != null ? Byte.valueOf(str1).byteValue() == str2 : false;
    }

    /**
     * 判断字符串转整形是否相等
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equals(String str1, int str2) {
        return str1 != null ? Integer.valueOf(str1).intValue() == str2 : false;
    }

    /**
     * 判断字符串转long是否相等
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equals(String str1, long str2) {
        return str1 != null ? Long.valueOf(str1).longValue() == str2 : false;
    }

    /**
     * 判断是否为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        if (isEmpty(str)) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isDigit(str.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * 匹配拆分此字符串
     *
     * @param str
     * @param separatorChar
     * @return
     */
    public static String[] split(String str, char separatorChar) {
        if (str == null) {
            return null;
        }
        int len = str.length();
        if (len == 0) {
            return new String[0];
        }
        List list = new ArrayList();
        int i = 0, start = 0;
        boolean match = false;
        boolean lastMatch = false;
        while (i < len) {
            if (str.charAt(i) == separatorChar) {
                if (match) {
                    list.add(str.substring(start, i));
                    match = false;
                    lastMatch = true;
                }
                start = ++i;
                continue;
            }
            lastMatch = false;
            match = true;
            i++;
        }
        if (match || lastMatch) {
            list.add(str.substring(start, i));
        }
        return (String[]) list.toArray(new String[list.size()]);
    }

    /**
     * 判断域名是否相等，支持多级域名
     *
     * @param domain
     * @param value
     * @return
     */
    public static boolean domainEqual(String domain, String value) {
        if (isEmpty(domain) || isEmpty(value)) {
            return false;
        }
        String[] array1 = split(domain, '.');
        String[] array2 = split(value, '.');
        if (array1.length == array2.length) {
            return equals(domain, value);
        } else {
            if (array1.length > array2.length) {
                int index = domain.indexOf(".");
                String temp = domain.substring(index + 1, domain.length());
                return equals(temp, value);
            } else {
                int index = value.indexOf(".");
                String temp = value.substring(index + 1, value.length());
                return equals(domain, temp);
            }
        }
    }

    /**
     * dividend除以divisor保留两位小数
     *
     * @param dividend
     * @param divisor
     * @return
     */
    public static String divisionTwoDecimalPlaces(String dividend, String divisor) {
        if (isEmpty(dividend) || isEmpty(divisor)) {
            return null;
        }
        if (equals(dividend, zero)) {
            return zero;
        }
        if (equals(divisor, zero)) {
            return one;
        }
        double result = Double.valueOf(dividend) / Double.valueOf(divisor);
        return decimalFormat.format(result);
    }

    /**
     * 判断字符串1是否包含字符串2
     *
     * @param str
     * @param searchStr
     * @return
     */
    public static boolean contains(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return false;
        }
        return str.indexOf(searchStr) >= 0;
    }

    public static void main(String[] args) {
        String a = "0";
        String b = "4";
        System.out.println(divisionTwoDecimalPlaces(a, b));
    }
}
