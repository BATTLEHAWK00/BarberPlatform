package controller;

import bean.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pojo.Admin;
import service.AdminService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/admin")
public class AdminController {
	private final AdminService adminService;

	@Autowired
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}

	@RequestMapping("/list")
	public Response getList() {
		Response response = new Response();
		response.setData(adminService.getAdminList());
		return response;
	}

	@RequestMapping(value = "/id/{adminid}/update", method = {RequestMethod.POST})
	public Response updateAdmin(
			@PathVariable("adminid") int adminid,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "gender", required = false) Integer gender,
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "remark", required = false) String remark,
			@RequestParam(value = "passwd", required = false) String passwd
	) {
		Admin admin = new Admin();
		admin.setAdminId(adminid);
		admin.setName(name);
		admin.setGender(gender);
		admin.setPhone(phone);
		admin.setRemark(remark);
		admin.setPasswd(passwd);
		adminService.updateAdmin(admin);
		return new Response();
	}
}
