package club.lemos.common.utils;

import org.springframework.lang.Nullable;
import org.springframework.util.NumberUtils;

public class NumberUtil extends NumberUtils {

    //-----------------------------------------------------------------------

    /**
     * <p>Convert a <code>String</code> to an <code>int</code>, returning
     * <code>zero</code> if the conversion fails.</p>
     *
     * <p>If the string is <code>null</code>, <code>zero</code> is returned.</p>
     *
     * <pre>
     *   NumberUtil.toInt(null) = 0
     *   NumberUtil.toInt("")   = 0
     *   NumberUtil.toInt("1")  = 1
     * </pre>
     *
     * @param str the string to convert, may be null
     * @return the int represented by the string, or <code>zero</code> if
     * conversion fails
     */
    public static int toInt(final String str) {
        return toInt(str, -1);
    }

    /**
     * <p>Convert a <code>String</code> to an <code>int</code>, returning a
     * default value if the conversion fails.</p>
     *
     * <p>If the string is <code>null</code>, the default value is returned.</p>
     *
     * <pre>
     *   NumberUtil.toInt(null, 1) = 1
     *   NumberUtil.toInt("", 1)   = 1
     *   NumberUtil.toInt("1", 0)  = 1
     * </pre>
     *
     * @param str          the string to convert, may be null
     * @param defaultValue the default value
     * @return the int represented by the string, or the default if conversion fails
     */
    public static int toInt(@Nullable final String str, final int defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(str);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /**
     * <p>Convert a <code>String</code> to a <code>long</code>, returning
     * <code>zero</code> if the conversion fails.</p>
     *
     * <p>If the string is <code>null</code>, <code>zero</code> is returned.</p>
     *
     * <pre>
     *   NumberUtil.toLong(null) = 0L
     *   NumberUtil.toLong("")   = 0L
     *   NumberUtil.toLong("1")  = 1L
     * </pre>
     *
     * @param str the string to convert, may be null
     * @return the long represented by the string, or <code>0</code> if
     * conversion fails
     */
    public static long toLong(final String str) {
        return toLong(str, 0L);
    }

    /**
     * <p>Convert a <code>String</code> to a <code>long</code>, returning a
     * default value if the conversion fails.</p>
     *
     * <p>If the string is <code>null</code>, the default value is returned.</p>
     *
     * <pre>
     *   NumberUtil.toLong(null, 1L) = 1L
     *   NumberUtil.toLong("", 1L)   = 1L
     *   NumberUtil.toLong("1", 0L)  = 1L
     * </pre>
     *
     * @param str          the string to convert, may be null
     * @param defaultValue the default value
     * @return the long represented by the string, or the default if conversion fails
     */
    public static long toLong(@Nullable final String str, final long defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Long.valueOf(str);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /**
     * <p>Convert a <code>String</code> to a <code>Double</code>
     *
     * @param value value
     * @return double value
     */
    public static Double toDouble(String value) {
        return toDouble(value, null);
    }

    /**
     * <p>Convert a <code>String</code> to a <code>Double</code>
     *
     * @param value        value
     * @param defaultValue 默认值
     * @return double value
     */
    public static Double toDouble(@Nullable String value, Double defaultValue) {
        if (value != null) {
            return Double.valueOf(value.trim());
        }
        return defaultValue;
    }

    /**
     * <p>Convert a <code>String</code> to a <code>Double</code>
     *
     * @param value value
     * @return double value
     */
    public static Float toFloat(String value) {
        return toFloat(value, null);
    }

    /**
     * <p>Convert a <code>String</code> to a <code>Double</code>
     *
     * @param value        value
     * @param defaultValue 默认值
     * @return double value
     */
    public static Float toFloat(@Nullable String value, Float defaultValue) {
        if (value != null) {
            return Float.valueOf(value.trim());
        }
        return defaultValue;
    }

    public static String encodeNumberToS(char[] digits, long i) {
        int radix = digits.length;
        char[] buf = new char[65];
        int charPos = 64;
        i = -i;
        while (i <= -radix) {
            buf[charPos--] = digits[(int) (-(i % radix))];
            i = i / radix;
        }
        buf[charPos] = digits[(int) (-i)];
        return new String(buf, charPos, (65 - charPos));
    }

    public static long decodeSToNumber(char[] digits, String str) {
        int radix = digits.length;
        long result = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            int digitValue = 0;
            for (int j = 0; j < radix; j++) {
                if (c == digits[j]) {
                    digitValue = j;
                    break;
                }
            }
            result = result * radix + digitValue;
        }
        return result;
    }

    /**
     * All possible chars for representing a number as a String
     */
    private final static char[] LOWER_DIGITS = {
            '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b',
            'c', 'd', 'e', 'f', 'g', 'h',
            'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z'
    };

    /**
     * All possible chars for representing a number as a String
     */
    private final static char[] DIGITS = {
            '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b',
            'c', 'd', 'e', 'f', 'g', 'h',
            'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F',
            'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z'
    };

    /**
     * 10 进制编码成 36 进制
     *
     * @param i 数字
     * @return 短字符串
     */
    public static String encode10To36(long i) {
        return encodeNumberToS(LOWER_DIGITS, i);
    }

    /**
     * 36 进制解码成 10 进制
     *
     * @param str 短字符串
     * @return 数字
     */
    public static long decode36To10(String str) {
        return decodeSToNumber(LOWER_DIGITS, str);
    }

    /**
     * 10 进制编码成 62 进制
     *
     * @param i 数字
     * @return 短字符串
     */
    public static String encode10To62(long i) {
        return encodeNumberToS(DIGITS, i);
    }

    /**
     * 62 进制解码成 10 进制
     *
     * @param str 短字符串
     * @return 数字
     */
    public static long decode62To10(String str) {
        return decodeSToNumber(DIGITS, str);
    }
}
