package cn.battlehawk233.barbershop.layer.service;

import cn.battlehawk233.barbershop.exception.ServiceException;
import cn.battlehawk233.barbershop.layer.dao.AdminRepo;
import cn.battlehawk233.barbershop.layer.dao.LoginLogRepo;
import cn.battlehawk233.barbershop.layer.entity.LoginLog;
import cn.hutool.core.util.HashUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class AuthService {
    private static final Logger logger = LoggerFactory.getLogger("AuthService");
    private final AdminRepo adminRepo;
    private final LoginLogRepo loginLogRepo;

    public String login(String username, String passwd, String loginIp, String loginUa) {
        var admin = adminRepo.findByName(username).orElseThrow(() -> new ServiceException("账号不存在"));

        String salt = admin.getSalt();
        var digest = DigestUtil.sha256Hex(passwd + salt);

        if (!admin.getPasswd().equals(digest)) {
            throw new ServiceException("密码错误");
        }

        Map<String, Object> payload = Map.ofEntries(
                Map.entry("adminId", admin.getId()),
                Map.entry("expireAt", System.currentTimeMillis() + 1000 * 60)
        );

        var jwt = JWTUtil.createToken(payload, "1234".getBytes());

        var loginLog = new LoginLog();
        loginLog.setLoginIp(loginIp);
        loginLog.setLoginUa(loginUa);
        loginLog.setAdmin(admin);
        loginLogRepo.save(loginLog);

        logger.info("管理员登录：{}", admin.getName());
        return jwt;
    }

    public void logout() {
        logger.info("管理员注销登录");
    }

    public void changePassword(int adminId, String passwd) {
        var admin = adminRepo.findById(adminId).orElseThrow(() -> new ServiceException("用户不存在"));
        var salt = IdUtil.fastSimpleUUID();
        var digest = DigestUtil.sha256Hex(passwd + salt);
        admin.setSalt(salt);
        admin.setPasswd(digest);
        adminRepo.save(admin);
        logger.info("管理员修改密码：{}", admin.getName());
    }

    public void switchAdmin() {

    }
}
