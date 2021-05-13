package controller;

import bean.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class LoginController {
    public Response handleLogin(){
        return new Response();
    }
}
