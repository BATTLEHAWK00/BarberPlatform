package cn.battlehawk233.barberplatform.controller;

import cn.battlehawk233.barberplatform.dao.SettingMapper;
import cn.battlehawk233.barberplatform.exceptions.ServiceException;
import cn.battlehawk233.barberplatform.util.SecurityUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/system")
@CrossOrigin(origins = "*", maxAge = 3600)
public class SystemController {
    private SettingMapper settingMapper;

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
}
