package com.example.smartcityb_2.bean;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/27 at 7:51
 */
public class Fwjl  {
    private String name,msg;

    public Fwjl(String name, String msg) {
        this.name = name;
        this.msg = msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
