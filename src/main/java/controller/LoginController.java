package controller;

import pojo.Admin;
import bean.Response;
import pojo.userInfo;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.AdminService;
import service.LoginService;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    @Setter
    private LoginService loginService;

    @Autowired
    @Setter
    private AdminService adminService;

    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public Response handleLogin(@RequestParam("username") String username,
                                @RequestParam("password") String passwd
    ) {
        Map<String, String> mp = new HashMap<>();
        String token = loginService.Login(username, passwd);
        mp.put("accessToken", token);
        return new Response(mp);
    }

    @RequestMapping("/userInfo")
    public Response userInfo(@RequestHeader("accessToken") String accessToken) {
        userInfo userInfo = new userInfo();
        userInfo.setRoles(new String[]{"admin"});
        userInfo.setAbility(new String[]{"READ", "WRITE", "DELETE"});
        userInfo.setUsername("admin");
        return new Response(userInfo);
    }

    @RequestMapping("/logout")
    public Response LogOut(@RequestHeader("accessToken") String accessToken) {
        loginService.Logout(accessToken);
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
