package com.example.smartcityb_2.bean;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 16:56
 */
public class PtjcBean {
    private String name;
    private int coolor;
    private int value;

    public PtjcBean(String name, int coolor, int value) {
        this.name = name;
        this.coolor = coolor;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCoolor() {
        return coolor;
    }

    public void setCoolor(int coolor) {
        this.coolor = coolor;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
