package com.xbcai;

public enum CommonEnum {
    TYPT("TYPT", "typt"),
    XFK("XFK", "xfk");
    // 成员变量
    private String key;
    private String value;

    // 构造方法
    private CommonEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
