package xyz.yahpoo.oneboot.common.kit;

import java.util.UUID;

/**
 * TODO ID生成辅助类
 */
public final class IdKit {

    private IdKit() {}

    private final static String[] CHARS = {
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    };

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");

    }

    public static String shortUuid() {
        StringBuffer sb = new StringBuffer();
        String uuid = uuid();
        String subStr;
        int hashCode;
        for (int i = 0; i < 8; i++) {
            subStr = uuid.substring(i * 4, i * 4 + 4);
            hashCode = subStr.hashCode();
            sb.append(CHARS[hashCode & (CHARS.length - 1)]);
        }
        return sb.toString();
    }

}
