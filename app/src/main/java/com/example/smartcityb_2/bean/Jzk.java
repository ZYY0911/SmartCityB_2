package com.example.smartcityb_2.bean;


import java.io.Serializable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/26 at 15:18
 */
public class Jzk  implements Serializable {

    /**
     * caseId : 1
     * name : 赵涵
     * sex : ddd
     * ID : 371402199902041133
     * birthday : 2020-11-23
     * tel : 22222233345
     * address : &hhh
     */

    private int caseId;
    private String name;
    private String sex;
    private String ID;
    private String birthday;
    private String tel;
    private String address;

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
