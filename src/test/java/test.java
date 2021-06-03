import bean.Order;
import dao.OrderMapper;
import dao.StoredValueMapper;
import exceptions.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.StoredValueService;

import java.math.BigDecimal;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)//使用junit4进行测试
@ContextConfiguration("classpath:applicationContext.xml")//加载配置文件
public class test {
    @Autowired
    private StoredValueService storedValueService;

    @Test
    public void Test() throws ServiceException {
        storedValueService.recharge(11, new BigDecimal(30), 1, "test");
    }
}
