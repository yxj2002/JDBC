package com.yxj.dao_.domain;

/**
 * @Author:zhili
 * @Time:2022/8/4 0:59
 * @describe:
 */
public class Actor {
    private String name;
    private Integer money;
    private int kity;

    public Actor() {
    }

    public Actor(String name, Integer money) {
        this.name = name;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
}
