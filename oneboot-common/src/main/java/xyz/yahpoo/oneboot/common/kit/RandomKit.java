package xyz.yahpoo.oneboot.common.kit;


import java.util.Random;

public final class RandomKit {

    private RandomKit() {}

    /**
     * TODO 获取指定长度的纯数字字符串
     * @param len
     * @return
     */
    public static String numStr(int len) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int count = 0;
        while (count < len) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
