package cn.battlehawk.barberplatform;

import cn.battlehawk233.barberplatform.dao.SettingMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {cn.battlehawk233.barberplatform.BarberPlatformApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BarberPlatformApplicationTests {
    @Autowired
    public SettingMapper settingsMapper;

    @Test
    public void testSettingMapper() {
        settingsMapper.setValue("JUnitTest", "JUnitTestValue");
        Assertions.assertEquals("JUnitTestValue", settingsMapper.getValue("JUnitTest"));
        settingsMapper.deleteSetting("JUnitTest");
    }
}
