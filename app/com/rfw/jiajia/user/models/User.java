package com.rfw.jiajia.user.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Index;

import com.rfw.common.base.models.BasicGenericModel;
import com.rfw.jiajia.user.constant.UserStatus;

/**
 * 
 * @author lizhong.chen
 * @date 2015年2月26日下午1:43:59
 * @description user
 * @version V1.0
 */
@Entity(name = User.TABLE_NAME)
public class User extends BasicGenericModel {

    public static final String TABLE_NAME = "user";

    @Id
    @GeneratedValue
    private Long uid;

    /**
     * 用户名
     */
    @Column(name = "user_name", columnDefinition = " varchar(64) not null", unique = true)
    @Index(name = "idx_user_name")
    private String userName;

    /**
     * 密码(md5加密)
     */
    @Column(name = "pwd", columnDefinition = " varchar(32)")
    private String pwd;

    private String email;

    /**
     * 状态 {@link UserStatus}
     */
    private Integer status;

    /**
     * 激活码
     */
    @Column(name = "validate_code")
    private String validateCode;

    /**
     * 激活时间
     */
    @Column(name = "validate_time")
    private Long validateTime;

    public User() {
    }

    public User(String userName, String pwd, String email, Integer status, String validateCode, Long validateTime) {
        this.userName = userName;
        this.pwd = pwd;
        this.email = email;
        this.status = status;
        this.validateCode = validateCode;
        this.validateTime = validateTime;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    public Long getValidateTime() {
        return validateTime;
    }

    public void setValidateTime(Long validateTime) {
        this.validateTime = validateTime;
    }

}
