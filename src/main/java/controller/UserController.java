package controller;

import bean.Response;
import exceptions.ServiceException;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pojo.User;
import service.UserService;

import java.util.Date;
import java.util.logging.Logger;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {
	Logger logger = Logger.getLogger("UserController");
	@Autowired
	@Setter
	private UserService userService;

	@RequestMapping(value = "/register", method = {RequestMethod.GET, RequestMethod.POST})
	public Response RegisterUser(
			@RequestParam("username") String username,
			@RequestParam("passwd") String passwd,
			@RequestParam("phone") String phone,
			@RequestParam(value = "gender", required = false) Integer gender,
			@RequestParam(value = "remark", required = false) String remark
	) {
		logger.info("用户注册:" + username);
		Response response = new Response();
		User user = new User();
		user.setUsername(username);
		user.setPasswd(passwd);
		user.setPhone(phone);
		user.setRemark(remark);
		user.setGender(gender);
		userService.registerUser(user);
		return response;
	}

	@RequestMapping(value = "/id/{uid}/passwd", method = {RequestMethod.PUT, RequestMethod.GET})
	public Response updatePasswd(@PathVariable("uid") int uid,
	                             @RequestParam("passwd") String passwd
	) {
		Response response = new Response();
		userService.updatePasswd(uid, passwd);
		return response;
	}

	@RequestMapping(value = "/id/{uid}/remark", method = {RequestMethod.PUT, RequestMethod.GET})
	public Response updateRemark(@PathVariable("uid") int uid,
	                             @RequestParam("remark") String remark
	) {
		Response response = new Response();
		userService.updateRemark(uid, remark);
		return response;
	}

	@RequestMapping(value = "/id/{uid}/phone", method = {RequestMethod.PUT, RequestMethod.GET})
	public Response updatePhone(@PathVariable("uid") int uid,
	                            @RequestParam("phone") String phone
	) {
		Response response = new Response();
		userService.updatePhone(uid, phone);
		return response;
	}

	@RequestMapping(value = "/id/{uid}/birthdate", method = {RequestMethod.PUT, RequestMethod.GET})
	public Response updateBirthDate(@PathVariable("uid") int uid,
	                                @RequestParam("date") Date date
	) {
		Response response = new Response();
		userService.updateBirthDate(uid, date);
		return response;
	}

	@RequestMapping(value = "/list", method = {RequestMethod.GET})
	public Response getUserList() {
		Response response = new Response();
		response.setData(userService.getUserList());
		return response;
	}

	@RequestMapping(value = "/id/{uid}", method = {RequestMethod.DELETE})
	public Response deleteUser(@PathVariable("uid") int uid) {
		userService.deleteUser(uid);
		return new Response();
	}

	@RequestMapping(value = "/id/{uid}/passwd/verify", method = {RequestMethod.GET, RequestMethod.POST})
	public Response verifyPasswd(@PathVariable("uid") int uid,
	                             @RequestParam("passwd") String passwd
	) {
		if (!userService.verifyPasswd(uid, passwd)) {
			throw new ServiceException("密码错误!", 400);
		}
		return new Response();
	}

	@RequestMapping(value = "/id/{userid}/update", method = {RequestMethod.POST})
	public Response updateUser(
			@PathVariable("userid") int userid,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "gender", required = false) Integer gender,
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "remark", required = false) String remark,
			@RequestParam(value = "passwd", required = false) String passwd,
			@RequestParam(value = "birthdate", required = false) String birthdate
	) {
		User user = new User();
		user.setUserid(userid);
		user.setUsername(name);
		user.setPhone(phone);
		user.setRemark(remark);
		user.setPasswd(passwd);
		user.setBirthdate(birthdate == null ? null : new Date(Long.parseLong(birthdate)));
		userService.updateUser(user);
		return new Response();
	}
}
