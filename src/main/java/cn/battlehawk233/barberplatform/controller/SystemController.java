package cn.battlehawk233.barberplatform.controller;

import cn.battlehawk233.barberplatform.dao.SessionMapper;
import cn.battlehawk233.barberplatform.dao.SettingMapper;
import cn.battlehawk233.barberplatform.exceptions.ServiceException;
import cn.battlehawk233.barberplatform.service.SessionService;
import cn.battlehawk233.barberplatform.util.SecurityUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/system")
@CrossOrigin(origins = "*", maxAge = 3600)
public class SystemController {
    private SettingMapper settingMapper;
    private SessionService sessionService;

    @RequestMapping("/firstTime")
    public String isFirstTime() {
        if (settingMapper.getValue("login.username") == null) {
            return "Yes";
        }
        return "No";
    }

    @RequestMapping("/setLoginToken")
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
        return true;
    }

    @RequestMapping("/login")
    public Map<String, String> Login(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        String accessToken = sessionService.Login(username, password);
        Map<String, String> data = new HashMap<>();
        data.put("accessToken", accessToken);
        return data;
    }

    @RequestMapping("/userInfo")
    public Map<String, Object> getUserInfo(@RequestParam("accessToken") String accessToken) {
        Map<String, Object> data = new HashMap<>();
        data.put("username", settingMapper.getValue("login.username"));
        data.put("roles", new String[]{"admin"});
        data.put("ability", new String[]{"READ", "WRITE", "DELETE"});
        return data;
    }
}
