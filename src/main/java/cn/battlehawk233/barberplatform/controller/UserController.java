package cn.battlehawk233.barberplatform.controller;

import cn.battlehawk233.barberplatform.bean.Response;
import cn.battlehawk233.barberplatform.exceptions.ServiceException;
import cn.battlehawk233.barberplatform.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.battlehawk233.barberplatform.service.UserService;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {
	final Logger logger = Logger.getLogger("UserController");

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/register", method = {RequestMethod.GET, RequestMethod.POST})
	public boolean RegisterUser(
			@RequestParam("username") String username,
			@RequestParam("passwd") String passwd,
			@RequestParam("phone") String phone,
			@RequestParam(value = "gender", required = false) Integer gender,
			@RequestParam(value = "remark", required = false) String remark
	) {
		logger.info("用户注册:" + username);
		User user = new User();
		user.setUsername(username);
		user.setPasswd(passwd);
		user.setPhone(phone);
		user.setRemark(remark);
		user.setGender(gender);
		userService.registerUser(user);
		return true;
	}

	@RequestMapping(value = "/id/{uid}/passwd", method = {RequestMethod.PUT, RequestMethod.GET})
	public boolean updatePasswd(@PathVariable("uid") int uid,
	                             @RequestParam("passwd") String passwd
	) {
		userService.updatePasswd(uid, passwd);
		return true;
	}

	@RequestMapping(value = "/list", method = {RequestMethod.GET})
	public List<User> getUserList() {
		return userService.getUserList();
	}

	@RequestMapping(value = "/id/{uid}", method = {RequestMethod.DELETE})
	public boolean deleteUser(@PathVariable("uid") int uid) {
		userService.deleteUser(uid);
		return true;
	}

	@RequestMapping(value = "/id/{uid}/passwd/verify", method = {RequestMethod.GET, RequestMethod.POST})
	public boolean verifyPasswd(@PathVariable("uid") int uid,
	                             @RequestParam("passwd") String passwd
	) {
		if (!userService.verifyPasswd(uid, passwd)) {
			throw new ServiceException("密码错误!", 400);
		}
		return true;
	}

	@RequestMapping(value = "/id/{userid}/update", method = {RequestMethod.POST})
	public boolean updateUser(
			@PathVariable("userid") int userid,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "gender", required = false) Integer gender,
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "remark", required = false) String remark,
			@RequestParam(value = "passwd", required = false) String passwd,
			@RequestParam(value = "birthdate", required = false) String birthdate
	) {
		User user = new User();
		user.setUserId(userid);
		user.setUsername(name);
		user.setPhone(phone);
		user.setRemark(remark);
		user.setPasswd(passwd);
		user.setBirthDate(birthdate == null ? null : new Date(Long.parseLong(birthdate)));
		userService.updateUser(user);
		return true;
	}
}
