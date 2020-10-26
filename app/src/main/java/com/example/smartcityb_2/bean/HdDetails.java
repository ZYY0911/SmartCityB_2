package com.example.smartcityb_2.bean;

import java.io.Serializable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 10:37
 */
public class HdDetails implements Serializable {

    /**
     * id : 1
     * typeid : 1
     * time : 2020-10-02 09:10:08
     * name : 百人书法比赛
     * count : 96
     * praiseCount : 1000
     * description : 中心广场大家一起书写中华经典诵读诗篇，弘扬中国文化，抒发爱国情怀
     * image : http://192.168.155.106:8080/mobileA/images/a1.jpg
     * recommand : 1
     * showImage : 1
     */

    private int id;
    private int typeid;
    private String time;
    private String name;
    private int count;
    private int praiseCount;
    private String description;
    private String image;
    private int recommand;
    private int showImage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(int praiseCount) {
        this.praiseCount = praiseCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getRecommand() {
        return recommand;
    }

    public void setRecommand(int recommand) {
        this.recommand = recommand;
    }

    public int getShowImage() {
        return showImage;
    }

    public void setShowImage(int showImage) {
        this.showImage = showImage;
    }
}
