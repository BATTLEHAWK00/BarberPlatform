package cn.battlehawk233.barberplatform.dao;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface SettingMapper {
    String getValue(@Param("key") String key);

    void setValue(@Param("key") String key, @Param("value") String value);

    Map<String, String> getSettings();
}
