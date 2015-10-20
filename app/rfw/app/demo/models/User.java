package rfw.app.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.Index;

import rfw.common.base.models.BasicModel;

/**
 * 
 * @author lizhong.chen
 * @date 2015年2月26日下午1:43:59
 * @description user
 * @version V1.0
 */
@Entity(name = User.TABLE_NAME)
public class User extends BasicModel {

    public static final String TABLE_NAME = "user";

    /**
     * 用户名
     */
    @Column(name = "name", columnDefinition = " varchar(64) not null", unique = true)
    @Index(name = "idx_use_name")
    private String name;

    /**
     * 密码(md5加密)
     */
    @Column(name = "pwd", columnDefinition = " varchar(32)")
    private String pwd;

    /**
     * 描述
     */
    @Column(name = "descr", columnDefinition = " varchar(2048)")
    private String descr;

    public User() {
    }

    public User(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

}
