package controller;

import bean.Response;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
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

	@Transactional
	@RequestMapping(value = "/id/{adminid}/update", method = {RequestMethod.POST})
	public Response updateAdmin(
			@PathVariable("adminid") int adminid,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "gender", required = false) Integer gender,
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "remark", required = false) String remark,
			@RequestParam(value = "passwd", required = false) String passwd
	) {
		if (name != null)
			adminService.updateName(adminid, name);
		if (phone != null)
			adminService.updatePhone(adminid, phone);
		if (remark != null)
			adminService.updateRemark(adminid, remark);
		if (gender != null)
			adminService.updateGender(adminid, gender);
		if (passwd != null)
			adminService.updatePasswd(adminid, passwd);
		return new Response();
	}
}
