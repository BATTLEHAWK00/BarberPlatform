import dao.OrderMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.AdminService;

@RunWith(SpringJUnit4ClassRunner.class)//使用junit4进行测试
@ContextConfiguration("classpath:applicationContext.xml")//加载配置文件
public class LogTest {
	Logger logger = LogManager.getLogger(LogTest.class);
	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private AdminService adminService;

	@Test
	public void test() {
		logger.error("asddd");
	}

	@Test
	public void testAssociation() {
		for (var i : orderMapper.getOrderList()) {
			System.out.println(i.hashCode());
		}
		System.out.println("asdasdasdasdas");
		for (var i : orderMapper.getOrderList()) {
			System.out.println(i.hashCode());
		}
	}

	@Test
	public void admin() {
		adminService.updatePasswd(4, "123456");
	}
}
