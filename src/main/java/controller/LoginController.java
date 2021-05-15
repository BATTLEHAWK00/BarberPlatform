package controller;

import bean.Response;
import bean.userInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/admin")
public class LoginController {
    @RequestMapping("/login")
    public Response handleLogin() {
        Map<String, String> mp = new HashMap<>();
        mp.put("accessToken", "admin-accessToken");
        return new Response(mp);
    }

    @RequestMapping("/userInfo")
    public Response userInfo(@RequestHeader("accessToken") String accessToken) {
        userInfo userInfo = new userInfo();
        userInfo.setRoles(new String[]{"admin"});
        userInfo.setAbility(new String[]{"READ", "WRITE", "DELETE"});
        userInfo.setUsername("asdasds");
        return new Response(userInfo);
    }
}
