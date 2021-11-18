package cn.battlehawk233.barberplatform.controller;

import cn.battlehawk233.barberplatform.dao.SettingMapper;
import cn.battlehawk233.barberplatform.exceptions.ServiceException;
import cn.battlehawk233.barberplatform.service.TokenService;
import cn.battlehawk233.barberplatform.util.SecurityUtil;
import cn.hutool.core.util.IdUtil;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/system")
@CrossOrigin(origins = "*", maxAge = 3600)
public class SystemController {
    public static final Logger logger = LoggerFactory.getLogger(SystemController.class);
    private SettingMapper settingMapper;
    private TokenService tokenService;

    @RequestMapping(value = "/firstTime", method = {RequestMethod.GET})
    public String isFirstTime() {
        if (settingMapper.getValue("login.username") == null) {
            return "Yes";
        }
        return "No";
    }

    @RequestMapping(value = "/setLoginToken", method = {RequestMethod.POST})
    @Transactional
    public boolean setLoginToken(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        if (settingMapper.getValue("login.username") != null) {
            throw new ServiceException("系统已初始化，请登录！", 403);
        }
        settingMapper.setValue("login.username", username);
        String salt = IdUtil.simpleUUID();
        settingMapper.setValue("login.salt", salt);
        String digest = SecurityUtil.getInstance().getDigestWithSalt(password, salt);
        settingMapper.setValue("login.password", digest);
        logger.info("后台已设置账号密码，完成初始化！");
        return true;
    }

    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public Map<String, String> Login(
            HttpServletRequest req,
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        String accessToken = tokenService.Login(req, username, password);
        Map<String, String> data = new HashMap<>();
        data.put("accessToken", accessToken);
        logger.info(String.format("后台登录：%s", accessToken));
        return data;
    }

    @RequestMapping(value = "/userInfo", method = {RequestMethod.POST})
    public Map<String, Object> getUserInfo(@RequestParam("accessToken") String accessToken) {
        Map<String, Object> data = new HashMap<>();
        data.put("username", settingMapper.getValue("login.username"));
        data.put("roles", new String[]{"admin"});
        data.put("ability", new String[]{"READ", "WRITE", "DELETE"});
        return data;
    }

    @RequestMapping(value = "/logout", method = {RequestMethod.POST})
    public String Logout(@RequestHeader("accessToken") String accessToken) {
        logger.info(String.format("后台注销登录：%s", accessToken));
        tokenService.Logout(accessToken);
        return "OK";
    }
}
