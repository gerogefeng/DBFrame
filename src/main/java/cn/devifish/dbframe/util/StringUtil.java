package cn.devifish.dbframe.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 * @author zhang
 */
public class StringUtil {

    /**
     * 检查字符串是否为空
     *
     * @param str String
     * @return boolean
     */
    public static boolean isEmpty(String str) {
        return null == str || ("".equals(str.trim()) || (str.length() <= 0));
    }

    /**
     * 检查字符串是否不为空
     *
     * @param str String
     * @return boolean
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 类型转换从String转向Date 格式由String类型的strFromat定义；默认格式为yyyy-MM-dd
     *
     * @param oldString 时间字符串
     * @param strFromat 格式化
     * @return newDate 日期
     */
    public static Date toDate(String oldString, String strFromat) {
        try {
            if (null == strFromat) {
                strFromat = "yyyy-MM-dd";
            }
            SimpleDateFormat bartDateFormat = new SimpleDateFormat(strFromat);
            Date newDate = null;
            if ((null != oldString) && (!oldString.equals(""))) {
                newDate = bartDateFormat.parse(oldString);
            }
            return newDate;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 检查字符串是否满足指定正则
     *
     * @param str String
     * @param regex 正则
     * @return boolean
     */
    public static boolean isMatch(String str, String regex) {
        return Pattern.compile(regex).matcher(str).matches();
    }

    /**
     * 验证字符串是否为一个电子邮箱地址
     *
     * @param email String
     * @return boolean
     */
    public static boolean isEmail(String email) {
        return isMatch(email, "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$");
    }

}
