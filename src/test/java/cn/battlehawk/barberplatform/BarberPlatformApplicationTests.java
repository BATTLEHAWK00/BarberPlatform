package cn.battlehawk.barberplatform;

import cn.battlehawk233.barberplatform.dao.SettingMapper;
import org.assertj.core.api.Fail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {cn.battlehawk233.barberplatform.BarberPlatformApplication.class})
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
