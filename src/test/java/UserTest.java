import exceptions.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pojo.StoredValueLog;
import pojo.User;
import service.StoredValueService;
import service.UserService;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)//使用junit4进行测试
@ContextConfiguration("classpath:applicationContext.xml")//加载配置文件
@Transactional
public class UserTest {
    @Autowired
    private StoredValueService storedValueService;

    @Autowired
    private UserService userService;

    private int userId;


    @Test
    public void init(){
        registerUser();
        checkPasswd();
        rechargeUser();
        costUser();
        getValueLogList();
    }

    public void registerUser() {
        User user = new User();
        user.setUsername("TestUser");
        user.setPasswd("123456");
        user.setPhone("12345678911111");
        user.setRemark("For Test Usage");
        userService.registerUser(user);
        userId = user.getUserid();
    }


    public void checkPasswd() {
        if (!userService.verifyPasswd(userId, "123456")) {
            throw new ServiceException("密码验证错误!", 400);
        }
    }


    public void rechargeUser() {
        for (int i = 0; i < 5; i++) {
            storedValueService.recharge(userId, new BigDecimal(30), 1, "For Test Use");
        }
    }


    public void costUser() {
        for (int i = 0; i < 5; i++) {
            storedValueService.costValue(userId, new BigDecimal(30), "For Test Use");
        }
        storedValueService.getLogListByUser(userId);
    }

    public void getValueLogList() {
        List<StoredValueLog> logs = storedValueService.getLogListByUser(userId);
        for (StoredValueLog log : logs) {
            System.out.println(log);
        }
    }
}
