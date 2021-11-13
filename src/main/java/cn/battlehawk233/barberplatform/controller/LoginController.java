package cn.battlehawk233.barberplatform.controller;

import cn.battlehawk233.barberplatform.bean.Response;
import cn.battlehawk233.barberplatform.pojo.Token;
import cn.battlehawk233.barberplatform.pojo.userInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.battlehawk233.barberplatform.pojo.Admin;
import cn.battlehawk233.barberplatform.service.AdminService;
import cn.battlehawk233.barberplatform.service.SessionService;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/admin")
public class LoginController {
    private final SessionService sessionService;
    private final AdminService adminService;

    @Autowired
    public LoginController(SessionService sessionService, AdminService adminService) {
        this.sessionService = sessionService;
        this.adminService = adminService;
    }

    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public Response handleLogin(@RequestParam("username") String username,
                                @RequestParam("password") String passwd
    ) {
        Map<String, String> mp = new HashMap<>();
        String token = sessionService.Login(username, passwd);
        mp.put("accessToken", token);
        return new Response(mp);
    }

    @RequestMapping(value = "/userInfo", method = {RequestMethod.GET, RequestMethod.POST})
    public Response userInfo(@RequestHeader("accessToken") String accessToken) {
        userInfo userInfo = new userInfo();
        userInfo.setRoles(new String[]{"admin"});
        userInfo.setAbility(new String[]{"READ", "WRITE", "DELETE"});
        Token token = sessionService.getSessionByToken(accessToken);
        Admin admin = adminService.getAdminByID(token.getAdminid());
        userInfo.setUsername(admin.getName());
        return new Response(userInfo);
    }

    @RequestMapping("/logout")
    public Response LogOut(@RequestHeader("accessToken") String accessToken) {
        sessionService.Logout(accessToken);
        return new Response();
    }

    @RequestMapping("/register")
    public Response Register(
            @RequestParam("username") String username,
            @RequestParam("passwd") String passwd,
            @RequestParam("phone") String phone,
            @RequestParam(value = "remark", required = false) String remark
    ) {
        Admin admin = new Admin();
        admin.setName(username);
        admin.setPasswd(passwd);
        admin.setPhone(phone);
        admin.setRemark(remark);
        adminService.createAdmin(admin);
        return new Response();
    }
}
