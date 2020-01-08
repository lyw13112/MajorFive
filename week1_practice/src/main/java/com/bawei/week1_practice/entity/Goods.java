package com.bawei.week1_practice.entity;

/**
 * @author 刘云蔚
 * @createTime 2019/12/30 18:50
 * @description
 */
public class Goods {
    private String name;
    private int price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Goods(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
