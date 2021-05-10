package controller;

import bean.Response;
import bean.User;
import exceptions.ServiceException;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

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
    ) throws ServiceException {
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
    ) throws ServiceException {
        Response response = new Response();
        userService.updatePasswd(uid, passwd);
        return response;
    }

    @RequestMapping(value = "/id/{uid}/remark", method = {RequestMethod.PUT, RequestMethod.GET})
    public Response updateRemark(@PathVariable("uid") int uid,
                                 @RequestParam("remark") String remark
    ) throws ServiceException {
        Response response = new Response();
        userService.updateRemark(uid, remark);
        return response;
    }

    @RequestMapping(value = "/id/{uid}/phone", method = {RequestMethod.PUT, RequestMethod.GET})
    public Response updatePhone(@PathVariable("uid") int uid,
                                @RequestParam("phone") String phone
    ) throws ServiceException {
        Response response = new Response();
        userService.updatePhone(uid, phone);
        return response;
    }

    @RequestMapping(value = "/id/{uid}/birthdate", method = {RequestMethod.PUT, RequestMethod.GET})
    public Response updateBirthDate(@PathVariable("uid") int uid,
                                    @RequestParam("date") String date
    ) throws ServiceException {
        Response response = new Response();
//        userService.updateBirthDate(uid, date);
        return response;
    }

    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    public Response getUserList() throws ServiceException {
        Response response = new Response();
        response.setData(userService.getUserList());
        return response;
    }

    @RequestMapping(value = "/id/{uid}", method = {RequestMethod.DELETE})
    public Response deleteUser(@PathVariable("uid") int uid) throws ServiceException {
        userService.deleteUser(uid);
        return new Response();
    }

    @RequestMapping(value = "/id/{uid}/passwd/verify", method = {RequestMethod.GET})
    public Response verifyPasswd(@PathVariable("uid") int uid,
                                 @RequestParam("passwd") String passwd
    ) throws ServiceException {
        Response response = new Response();
        boolean flag = userService.verifyPasswd(uid, passwd);
        response.setData(flag);
        return response;
    }
}
