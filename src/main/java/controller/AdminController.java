package controller;

import bean.Response;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.AdminService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    @Setter
    private AdminService adminService;

    @RequestMapping("/list")
    public Response getList() {
        Response response = new Response();
        response.setData(adminService.getAdminList());
        return response;
    }
}
