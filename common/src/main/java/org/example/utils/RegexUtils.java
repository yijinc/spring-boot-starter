package org.example.utils;

import java.util.Objects;
import java.util.regex.Pattern;

public class RegexUtils {
    private RegexUtils() {}

    /**
     * 邮箱验证
     */
    public static final String EMAILREGULAR = "^((([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+(\\.([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+)*)|((\\x22)((((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(([\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x7f]|\\x21|[\\x23-\\x5b]|[\\x5d-\\x7e]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(\\\\([\\x01-\\x09\\x0b\\x0c\\x0d-\\x7f]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF]))))*(((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(\\x22)))@((([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.)+(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))$";

    /**
     * 手机验证
     */
    public static final String PHONEREGULAR = "(^(0|86|17951)?((13[0-9]|15[012356789]|16[2567]|17[012345678]|18[0-9]|14[01456789]|19[012356789])[0-9]{8})$)";

    /**
     * 判断是否是邮箱
     *
     * @param email 邮箱
     * @return boolean
     */
    public static boolean isEmail(String email) {
        if (Objects.isNull(email) || email.isBlank()) {
            return false;
        }
        email = email.toLowerCase().trim();
        Pattern compile = Pattern.compile(EMAILREGULAR, Pattern.DOTALL);
        return compile.matcher(email).matches();
    }

    /**
     * 判断是否是手机号
     *
     * @param phone 手机号
     * @return boolean
     */
    public static boolean isPhone(String phone) {
        if (Objects.isNull(phone) || phone.isBlank()) {
            return false;
        }
        Pattern compile = Pattern.compile(PHONEREGULAR, Pattern.DOTALL);
        return compile.matcher(phone).matches();
    }

}
