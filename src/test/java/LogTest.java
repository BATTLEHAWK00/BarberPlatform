import org.apache.ibatis.logging.log4j.Log4jImpl;
import org.apache.logging.log4j.LogManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.logging.LoggerFactory;
import org.apache.logging.log4j.Logger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)//使用junit4进行测试
@ContextConfiguration("classpath:applicationContext.xml")//加载配置文件
public class LogTest {
    Logger logger = LogManager.getLogger(LogTest.class);

    @Test
    public void test() {
        logger.error("asddd");
    }
}
