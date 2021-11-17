package cn.battlehawk233.barberplatform.controller;

import cn.battlehawk233.barberplatform.bean.Response;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.battlehawk233.barberplatform.pojo.Admin;
import cn.battlehawk233.barberplatform.service.AdminService;

import java.util.List;

@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/admin")
public class AdminController {
	private final AdminService adminService;

	@RequestMapping("/list")
	public List<Admin> getList() {
		return adminService.getAdminList();
	}

	@RequestMapping(value = "/id/{adminid}/update", method = {RequestMethod.POST})
	public boolean updateAdmin(
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
		return true;
	}
}
