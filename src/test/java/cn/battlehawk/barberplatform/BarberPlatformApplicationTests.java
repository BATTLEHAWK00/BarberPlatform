package cn.battlehawk.barberplatform;

import cn.battlehawk233.barberplatform.dao.SettingMapper;
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
        settingsMapper.setValue("asd", "111");
        System.out.println(settingsMapper.getValue("asd"));
        System.out.println(settingsMapper.getSettings());
    }
}
