package com.guanshj.enums;

/**
 * 双色球类似枚举
 * 创建日期: 14-7-14  上午10:17
 *
 * @author: guanshj QQ: 928990049
 */
public enum BallType {

    RED1("红球", 11),
    RED2("红球", 12),
    RED3("红球", 13),
    RED4("红球", 14),
    RED5("红球", 15),
    RED6("红球", 16),
    RED("红球", 1),
    BLUE("蓝球", 2);


    // 成员变量
    private String name;
    private int code;

    // 构造方法
    private BallType(String name, int code) {
        this.name = name;
        this.code = code;
    }

    // 普通方法 根据code获取name
    public static String getName(int code) {
        for (BallType c : BallType.values()) {
            if (c.getCode() == code) {
                return c.name;
            }
        }
        return null;
    }

    // get set 方法
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
