package com.yxj.dao_.domain;

/**
 * @Author:zhili
 * @Time:2022/8/3 15:18
 * @describe:
 */
public class Temp {
    private Integer id;
    private String name;

    public Temp() {
    }

    public Temp(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Temp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
