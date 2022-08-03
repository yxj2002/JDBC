package com.yxj.dao_.domain;

/**
 * @Author:zhili
 * @Time:2022/8/2 18:19
 * @describe:
 */
public class Admire {
    private Integer id;
    private String name;
    private String content;

    public Admire() {
    }

    public Admire(int id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Admire{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
