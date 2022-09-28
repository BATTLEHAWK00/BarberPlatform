package cn.battlehawk233.barbershop.layer.service;

import cn.battlehawk233.barbershop.exception.ServiceException;
import cn.battlehawk233.barbershop.layer.dao.AdminRepo;
import cn.battlehawk233.barbershop.layer.dao.LoginLogRepo;
import cn.battlehawk233.barbershop.layer.entity.Admin;
import cn.battlehawk233.barbershop.layer.entity.LoginLog;
import cn.battlehawk233.barbershop.layer.entity.LoginType;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.DigestUtil;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private static final Logger logger = LoggerFactory.getLogger("AuthService");
    private final AdminRepo adminRepo;
    private final LoginLogRepo loginLogRepo;
    private final AuthenticationManager authManager;

    private void saveLoginLog(Admin admin, String ip, String ua, LoginType type) {
        var loginLog = new LoginLog();
        loginLog.setLoginIp(ip);
        loginLog.setLoginUa(ua);
        loginLog.setAdmin(admin);
        loginLog.setLoginType(LoginType.PC);
        loginLogRepo.save(loginLog);
    }

    public String login(String username, String passwd, String loginIp, String loginUa) {
        var context = SecurityContextHolder.getContext();
        try {
            if (context.getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("loggedIn"))) {
                throw new ServiceException("你已登录，无需重复登录");
            }
            var admin = adminRepo.findByName(username).orElseThrow(() -> new ServiceException("账号不存在"));
            var authToken = new UsernamePasswordAuthenticationToken(username, passwd);
            var auth = authManager.authenticate(authToken);

            SecurityContextHolder.getContext().setAuthentication(auth);
            saveLoginLog(admin, loginIp, loginUa, LoginType.PC);

            logger.info("管理员登录：{}", admin.getName());
            return (String) auth.getCredentials();
        } catch (BadCredentialsException e) {
            throw new ServiceException("密码错误");
        }
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
