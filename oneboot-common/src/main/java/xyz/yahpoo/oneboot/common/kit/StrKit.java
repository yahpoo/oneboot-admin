package xyz.yahpoo.oneboot.common.kit;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrKit {

    private static final char UNDERLINE = '_';
    private static final char HYPHEN_LINE = '-';

    /**
     * TODO 字符串空判断
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return null == str || "".equals(str.trim());
    }

    /**
     * TODO 字符串非空判断
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * TODO 去除空格
     * @param sourceStr
     * @return
     */
    public static String trim(String sourceStr) {
        if (isEmpty(sourceStr)) {
            return null;
        } else {
            return sourceStr.replaceAll(" ", "");
        }
    }

    /**
     * TODO 过滤字符串
     * @param str
     * @return
     */
    public static String filterStr(String str) {
        if (isEmpty(str)) {
            return str;
        } else {
            str = str.replaceAll(";", "");
            str = str.replaceAll("%", "");
            str = str.replaceAll("--", "");
            str = str.replaceAll("/", "");
            str = str.replaceAll("=", "");
            str = str.replaceAll("'", "&#39;");
            str = str.replaceAll("\\(", "&#40;").replace("\\)", "&#41;");
            str = str.replaceAll("<", "&lt");
            str = str.replaceAll(">", "&gt");
            return str;
        }
    }


    /**
     * TODO 过滤XSS
     * @param value
     * @return
     */
    public static String cleanXSS(String value) {
        if (null == value) {
            return value;
        } else {
            value = value.replaceAll("\\bselect\\b", "invalid character");
            value = value.replaceAll("\\band\\b", "invalid character");
            value = value.replaceAll("\\bor\\b", "invalid character");
            value = value.replaceAll("\\bdelete\\b", "invalid character");
            value = value.replaceAll("\\bjoin\\b", "invalid character");
            value = value.replaceAll("\\bdrop\\b", "invalid character");

            value = value.replaceAll("\\+", "&#43;");
            value = value.replaceAll("&", "&amp;");
            value = value.replaceAll("%", "&#37;");
            // value = value.replaceAll("\"","&quot;");
            value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
            value = value.replaceAll("%3C", "&lt;").replaceAll("%3E", "&gt;");
            value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
            value = value.replaceAll("%28", "&#40;").replaceAll("%29", "&#41;");
            value = value.replaceAll("'", "&#39;");
            value = value.replaceAll("alert", "invalid character");
            value = value.replaceAll("eval\\((.*)\\)", "invalid character");
            value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
            value = value.replaceAll("<\\s*script", "invalid character");
            value = value.replaceAll("location.href", "invalid character");
        }
        return value;
    }

    /**
     * TODO 驼峰转下划线
     * @param param
     * @return
     */
    public static String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int length = param.length();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * TODO 下划线转驼峰
     * @param param
     * @return
     */
    public static String underlineToCamel(String param) {
        return toCamel(param, UNDERLINE);
    }

    /**
     * TODO 横线转驼峰
     * @param param
     * @return
     */
    public static String hyphenLineToCamel(String param) {
        return toCamel(param, HYPHEN_LINE);
    }

    /**
     * Camel case
     *
     * @param s characters
     * @return String after Camel case
     */
    public static String toCapitalizeCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = underlineToCamel(s);
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    /**
     * TODO 首字母大写
     * @param param
     * @return
     */
    public static String firstToUpperCase(String param) {
        char[] cs = param.toCharArray();
        if (cs[0] > 96 && cs[0] < 123) {
            cs[0] -= 32;
        }
        return String.valueOf(cs);
    }

    /**
     * TODO 首字母小写
     * @param param
     * @return
     */
    public static String firstToLowerCase(String param) {
        char[] cs = param.toCharArray();
        if (cs[0] > 64 && cs[0] < 91) {
            cs[0] += 32;
        }
        return String.valueOf(cs);
    }

    /**
     * TODO URL编码
     * @param str
     * @return
     */
    public static String urlDecode(String str) {
        if (isEmpty(str)) {
            return null;
        } else {
            try {
                return java.net.URLDecoder.decode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    /**
     * TODO 2进制字符串转16进制字符串
     * @param bString
     * @return
     */
    public static String binaryStr2hexStr(String bString) {
        if (bString == null || bString.equals("") || bString.length() % 8 != 0) {
            return null;
        }
        StringBuilder tmp = new StringBuilder();
        int iTmp;
        for (int i = 0; i < bString.length(); i += 4) {
            iTmp = 0;
            for (int j = 0; j < 4; j++) {
                iTmp += Integer.parseInt(bString.substring(i + j, i + j + 1)) << (4 - j - 1);
            }
            tmp.append(Integer.toHexString(iTmp));
        }
        return tmp.toString();
    }

    /**
     * TODO 16进制字符串转2进制字符串
     * @param hexString
     * @return
     */
    public static String hexStr2binaryStr(String hexString) {
        if (hexString == null || hexString.length() % 2 != 0) {
            return null;
        }
        String bString = "", tmp;
        for (int i = 0; i < hexString.length(); i++) {
            tmp = "0000" + Integer.toBinaryString(Integer.parseInt(hexString.substring(i, i + 1), 16));
            bString += tmp.substring(tmp.length() - 4);
        }
        return bString;
    }

    /**
     * TODO 字符串填充参数
     * @param str
     * @param arr
     * @return
     */
    private static String fillStrByArgs(String str, String[] arr) {
        Matcher m = Pattern.compile("\\{(\\d)\\}").matcher(str);
        while (m.find()) {
            str = str.replace(m.group(), arr[Integer.parseInt(m.group(1))]);
        }
        return str;
    }

    /**
     * TODO 去掉左右空格
     * @param str
     * @return
     */
    public static String trimBlank(String str) {
        if (isEmpty(str)) {
            return null;
        } else {
            return str.replaceAll("^[　 ]+|[　 ]+$", "");
        }
    }

    public static int length(String str) {
        if (isEmpty(str)) {
            return 0;
        } else {
            return str.length();
        }
    }

    /**
     * TODO 生成指定长度的随机整数
     *
     * @param length int
     * @return String
     */
    public static String createRandom(int length) {
        double a = Math.pow(10, length - 1);
        int num = (int) ((Math.random() * 9 + 1) * a);
        return String.valueOf(num);
    }

    /**
     * TODO 删除查询关键字中的单引号或双引号以避免sql错误
     * @param str
     * @return
     */
    public static String removeQuotes(String str) {
        if (isNotEmpty(str)) {
            return str.replace("'", "").replace("\"", "");
        } else {
            return "";
        }
    }

    /**
     * TODO 替换html的特殊字符
     * @param html
     * @return
     */
    public static String replaceHtml(String html) {
        if (isEmpty(html)) {
            return "";
        }
        String regEx = "<.+?>";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(html);
        String s = m.replaceAll("");
        return s;
    }

    /**
     * TODO 指定字符串转驼峰
     * @param param
     * @param s
     * @return
     */
    private static String toCamel(String param, char s) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int length = param.length();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char c = param.charAt(i);
            if (c == s) {
                if (++i < length) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

}
