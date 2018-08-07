package cn.sanlicun.pay.param;

import cn.sanlicun.pay.model.BaseModel;

/**
 * Created by Mr Ding on 2018/8/7.
 */

public class LoginParam extends BaseModel {
    private  String username;
    private  String pwd;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
