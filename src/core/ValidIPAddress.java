package core;

public class ValidIPAddress {

    public static void main(String[] args) {
        boolean digit = Character.isDigit('1');

        System.out.println((int)'1');

        ValidIPAddress validIPAddress = new ValidIPAddress();
        String s = validIPAddress.validIPAddress("192.168.1.1.");

        System.out.println(s);
    }

    public String validIPAddress(String queryIP) {
        if (queryIP.indexOf('.') >= 0) {
            return isIpV4(queryIP) ? "IPv4" : "Neither";
        } else {
            return isIpV6(queryIP) ? "IPv6" : "Neither";
        }
    }

    public boolean isIpV4(String queryIP) {
        //加-1是防止出现空字符串无法计数 比如192.168.1.1. 后边多了一个点，不加-1会被忽略后边的空串
        String[] split = queryIP.split("\\.",-1);
        //个数不是4个
        if (split.length != 4) {
            return false;
        }
        for (String s : split) {
            //每个长度不在 1-3之间
            if (s.length() > 3 || s.length() == 0) {
                return false;
            }
            //有前导0 并且长度不为1
            if (s.charAt(0) == '0' && s.length() != 1) {
                return false;
            }
            //计算数字
            int ans = 0;
            for (int j = 0; j < s.length(); j++) {
                char c = s.charAt(j);
                //不是数字
                if (!Character.isDigit(c)) {
                    return false;
                }
                ans = ans * 10 + (c - '0');
            }
            //数字超过255
            if (ans > 255) {
                return false;
            }
        }
        return true;
    }

    public boolean isIpV6(String queryIP) {
        String[] split = queryIP.split(":",-1);
        //数量不是8个
        if (split.length != 8) {
            return false;
        }
        for (String s : split) {
            //每个串 长度不在1-4之间
            if (s.length() > 4 || s.length() == 0) {
                return false;
            }
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                //不是数字并且字母不在 a-f之间
                if (!Character.isDigit(c) && !(Character.toLowerCase(c) >= 'a') || !(Character.toLowerCase(c) <= 'f')) {
                    return false;
                }
            }
        }
        return true;
    }

}
