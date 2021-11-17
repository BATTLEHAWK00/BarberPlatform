package cn.battlehawk233.barberplatform.util;

import cn.hutool.crypto.SecureUtil;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.UUID;

public class SecurityUtil {
    public static final SecurityUtil instance = new SecurityUtil();

    public static SecurityUtil getInstance() {
        return instance;
    }
    /**
     * 获得加盐MD5
     *
     * @param str  目标字符串
     * @param salt 盐
     * @return 返回加盐MD5
     */
    public String getDigestWithSalt(String str, String salt) {
        String sha1 = SecureUtil.sha1(str + salt);
        for (int i = 0; i < 3; i++) {
            sha1 = SecureUtil.sha1(sha1 + salt);
        }
        return sha1;
    }
}
