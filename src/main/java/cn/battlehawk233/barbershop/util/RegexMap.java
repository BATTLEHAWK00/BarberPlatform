package cn.battlehawk233.barbershop.util;

import java.util.regex.Pattern;

public enum RegexMap {
    USERNAME("^[a-zA-Z][a-zA-Z0-9_]{4,15}$", "字母开头，允许5-16字节，允许字母数字下划线"),
    PASSWORD("^[a-zA-Z]\\w{5,17}$", "以字母开头，长度在6~18之间，只能包含字母、数字和下划线"),
    PHONE("^[0-9]{11}$", "11位手机号"),
    CHINESE_NAME("^[\\u4e00-\\u9fa5]{2,5}$", "中文姓名，2~5字");

    private final Pattern pattern;
    private final String hint;

    public String getHint() {
        return hint;
    }

    public Pattern getPattern() {
        return pattern;
    }

    RegexMap(String regex, String hint) {
        this.pattern = Pattern.compile(regex);
        this.hint = hint;
    }
}
