package com.example.smartcityb_2.bean;

import java.io.Serializable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 16:33
 */
public class YylItem  implements Serializable {
    private int image;
    private String name,address;

    public YylItem(int image, String name, String address) {
        this.image = image;
        this.name = name;
        this.address = address;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
