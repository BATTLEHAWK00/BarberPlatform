package util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.UUID;

public class SecurityUtil {
    public static final SecurityUtil instance = new SecurityUtil();

    public static SecurityUtil getInstance() {
        return instance;
    }

    /**
     * 生成MD5
     *
     * @param str 目标字符串
     * @return 返回字符串MD5
     */
    public String getMD5(String str) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] md5_Bytes = md5.digest(str.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int md5_byte : md5_Bytes) {
                int n = md5_byte;
                if (n < 0) n += 256;
                if (n < 16) sb.append("0");
                sb.append(Integer.toHexString(n));
            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获得加盐MD5
     *
     * @param str  目标字符串
     * @param salt 盐
     * @return 返回加盐MD5
     */
    public String getSaltMD5(String str, String salt) {
        String md5 = getMD5(str + salt);
        for (int i = 0; i < 3; i++) {
            md5 = getMD5(md5 + salt);
        }
        return md5;
    }

    public String genUUID32() {
        String[] uuids = UUID.randomUUID().toString().split("-");
        StringBuilder sb = new StringBuilder();
        for (String i : uuids) {
            sb.append(i);
        }
        return sb.toString();
    }
    public String genUUID8() {
        return genUUID32().substring(0,8);
    }
}
