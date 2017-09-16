package com.oak.model;

/**
 * Created by Chennl on 6/23/2017.
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="api_user")
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="id")
    private long id;
    @Column(name="username")
    private String userName;
    @Column(name="password")
    private String password;
    @Column(name="role")
    private String role;
    @Column(name="fullname")
    private String fullName;
    @Column(name="email")
    private String email;
    @Column(name="enabled")
    private short enabled;

//    public UserInfo() {}
//
//    public UserInfo(long id, String userName, String password, String role, String fullName, String email, short enabled) {
//        this.id = id;
//        this.userName = userName;
//        this.password = password;
//        this.role = role;
//        this.fullName = fullName;
//        this.email = email;
//        this.enabled = enabled;
//    }

    public long getUserId() {
        return id;
    }
    public void setUserId(long id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public short getEnabled() {
        return enabled;
    }
    public void setEnabled(short enabled) {
        this.enabled = enabled;
    }
}