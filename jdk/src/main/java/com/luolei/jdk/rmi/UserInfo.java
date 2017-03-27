package com.luolei.jdk.rmi;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * @author luolei
 * @date 2017-03-13 15:16
 */
public class UserInfo implements Serializable {

    private static final long serialVersionUID = -2493978268235579164L;

    private String userName;
    private String userDesc;
    private Integer userAge;
    private Boolean userSex;

    public UserInfo() throws RemoteException {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = username;
    }

    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public Boolean getUserSex() {
        return userSex;
    }

    public void setUserSex(Boolean userSex) {
        this.userSex = userSex;
    }

}
