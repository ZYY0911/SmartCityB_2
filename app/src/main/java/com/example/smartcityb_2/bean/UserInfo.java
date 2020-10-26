package com.example.smartcityb_2.bean;

import java.io.Serializable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 9:15
 */
public class UserInfo implements Serializable {

    /**
     * id : 371402199902041133
     * name : 赵子
     * avatar : http://192.168.155.106:8080/mobileA/images/user8.png
     * phone : 211t58888
     * sex : female
     */

    private String id;
    private String name;
    private String avatar;
    private String phone;
    private String sex;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
