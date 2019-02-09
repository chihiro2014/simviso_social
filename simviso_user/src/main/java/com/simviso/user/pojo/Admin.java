package com.simviso.user.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @description: simviso_parent
 * @author: AAR
 * @date: 2/9/2019
 * @Email: liujch1996@gmail.com
 */
@Entity
@Table(name = "tb_admin")
public class Admin implements Serializable {


    @Id
    private String id;
    private String loginname;
    private String password;
    private String state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


}
